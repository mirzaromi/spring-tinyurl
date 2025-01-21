package org.mirza.tinyurl.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mirza.tinyurl.entity.Url;
import org.mirza.tinyurl.exception.GlobalException;
import org.mirza.tinyurl.exception.NotFound;
import org.mirza.tinyurl.repository.UrlRepository;
import org.mirza.tinyurl.util.Base62Encoder;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class UrlService {

    private final UrlRepository urlRepository;
    private final UrlValidationService urlValidationService;

    @CachePut(value = "tinyUrlCache")
    public String generateEncodedUrl(final String longUrl) {

        // check if url exist
        boolean isUrlValid = urlValidationService.isUrlValid(longUrl);
        if (!isUrlValid) {
            throw new GlobalException("Please input a valid URL");
        }

        // generate short url
        long id = System.currentTimeMillis();
        String shortUrl = Base62Encoder.encode(id);
        log.info("Generated url id: {} with shortUrl: {}", id, shortUrl);

        // set expiration to one year later
        LocalDateTime expDate = LocalDateTime.now();
        expDate = expDate.plusYears(1);
        log.info("set expiration date: {}", expDate);

        // set url object for saving the url
        Url url = new Url();
        url.setLongUrl(longUrl);
        url.setShortUrl(shortUrl);
        url.setExpiresAt(expDate);

        urlRepository.save(url);

        return shortUrl;
    }

    @Cacheable(value = {"tinyUrlCache"})
    public String getLongUrl(final String shortUrl) {
        Url url = urlRepository.findByShortUrl(shortUrl)
                .orElseThrow(() -> new NotFound("Url not found"));

        LocalDateTime now = LocalDateTime.now();
        if (now.isAfter(url.getExpiresAt())) {
            throw new GlobalException("Url is expired");
        }

        log.info("get long url: {}", url);
        return url.getLongUrl();
    }

}
