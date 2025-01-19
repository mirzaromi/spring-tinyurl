package org.mirza.tinyurl.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mirza.tinyurl.repository.UrlRepository;
import org.mirza.tinyurl.util.Base62Encoder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UrlServiceTest {
    @Mock
    UrlRepository urlRepository;

    @InjectMocks
    UrlService urlService;

    @Spy
    Base62Encoder base62Encoder;

    @BeforeEach
    void setUp() {
    }

    @Test
    void success_generateEncodedUrl() {

    }

    @Test
    void failed_generateEncodedUrl_urlNotFound() {

        assertThrows(Exception.class, () -> urlService.generateEncodedUrl(""));
    }

    @Test
    void getLongUrl() {
    }
}