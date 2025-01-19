package org.mirza.tinyurl.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mirza.tinyurl.entity.Url;
import org.mirza.tinyurl.exception.NotFound;
import org.mirza.tinyurl.repository.UrlRepository;
import org.mirza.tinyurl.util.Base62Encoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class UrlService {

    public static final String PROTOCOL = "http://";

    private final UrlRepository urlRepository;

    public String generateEncodedUrl(final String longUrl) {
        // generate short url
        long id = System.currentTimeMillis();
        String shortUrl = PROTOCOL + Base62Encoder.encode(id);

        // set expiration to one year later
        LocalDateTime now = LocalDateTime.now();
        now.plusYears(1);

        // set url object for saving the url
        Url url = new Url();
        url.setLongUrl(longUrl);
        url.setShortUrl(shortUrl);
        url.setExpiresAt(now);

        urlRepository.save(url);

        return shortUrl;
    }

    public String getLongUrl(final String shortUrl) {
        Url url = urlRepository.findByShortUrl(shortUrl).orElseThrow(() -> new NotFound("Url not found"));

        return url.getLongUrl();
    }

}
