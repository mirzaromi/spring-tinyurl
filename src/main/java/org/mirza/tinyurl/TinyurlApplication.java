package org.mirza.tinyurl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class TinyurlApplication {

    public static void main(String[] args) {
        SpringApplication.run(TinyurlApplication.class, args);
    }

}
