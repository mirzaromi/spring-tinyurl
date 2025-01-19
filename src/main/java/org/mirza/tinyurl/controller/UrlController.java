package org.mirza.tinyurl.controller;

import lombok.RequiredArgsConstructor;
import org.mirza.tinyurl.dto.BaseResponse;
import org.mirza.tinyurl.service.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/url-shortener")
@RequiredArgsConstructor
public class UrlController {

    private final UrlService urlService;

    @PostMapping("shorten")
    public ResponseEntity<BaseResponse> shortenUrl(String longUrl) {

        String shortUrl = urlService.generateEncodedUrl(longUrl);

        BaseResponse<String> response = new BaseResponse<>();
        response.setCode(HttpStatus.OK.value());
        response.setMessage("Successfully shortened URL");
        response.setData(shortUrl);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/get-long-url")
    public ResponseEntity<BaseResponse> getLongUrl(String shortUrl) {
        String longUrl = urlService.getLongUrl(shortUrl);

        BaseResponse<String> response = new BaseResponse<>();
        response.setCode(HttpStatus.OK.value());
        response.setMessage("Successfully shortened URL");
        response.setData(longUrl);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
