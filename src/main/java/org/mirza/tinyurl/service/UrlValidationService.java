package org.mirza.tinyurl.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

@Service
@Slf4j
public class UrlValidationService {
    public boolean isUrlValid(String urlString) {
        try {
            log.info("Checking if URL is valid: {}", urlString);
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("HEAD");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);

            int responseCode = connection.getResponseCode();
            return (responseCode >= 200 && responseCode < 400);

        } catch (MalformedURLException e) {
            log.error("Invalid URL format: {}", urlString, e);
            return false;
        } catch (IOException e) {
            log.error("Error checking URL {}: {}", urlString, e.getMessage());
            return false;
        }
    }
}
