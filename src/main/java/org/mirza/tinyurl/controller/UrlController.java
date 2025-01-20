package org.mirza.tinyurl.controller;

import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.mirza.tinyurl.dto.BaseResponse;
import org.mirza.tinyurl.service.UrlService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequestMapping("/url-shortener")
@RequiredArgsConstructor
public class UrlController {

    private final UrlService urlService;

    @PostMapping("shorten")
    public ResponseEntity<BaseResponse<String>> shortenUrl(@NotBlank String longUrl) {

        String shortUrl = urlService.generateEncodedUrl(longUrl);

        BaseResponse<String> response = new BaseResponse<>();
        response.setCode(HttpStatus.OK.value());
        response.setMessage("Successfully shortened URL");
        response.setData(shortUrl);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/get-long-url")
    public ResponseEntity<BaseResponse<String>> getLongUrl(String shortUrl) {
        String longUrl = urlService.getLongUrl(shortUrl);

        BaseResponse<String> response = new BaseResponse<>();
        response.setCode(HttpStatus.OK.value());
        response.setMessage("Successfully shortened URL");
        response.setData(longUrl);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/redirect/{shortUrl}")
    public RedirectView getRedirect(@PathVariable String shortUrl) {
        // get long url
        String longUrl = urlService.getLongUrl(shortUrl);

        return new RedirectView(longUrl);
    }
}
