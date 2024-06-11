package com.learning.practice.services.impl;

import org.apache.camel.ProducerTemplate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class EmailServiceImplTest {

    @Mock
    private ProducerTemplate producerTemplate;

    @InjectMocks
    private EmailServiceImpl emailService;

    @BeforeEach
    void setUp() {
        ReflectionTestUtils.setField(emailService, "from", "test@example.com");
    }

    @Test
    void sendEmail() {
        String recipient = "recipient@example.com";
        String subject = "Test Subject";
        String body = "Test Body";
        Map<String, Object> expectedHeaders = new HashMap<>();
        expectedHeaders.put("to", recipient);
        expectedHeaders.put("from", "test@example.com");
        expectedHeaders.put("subject", subject);
        expectedHeaders.put("body", body);
        emailService.sendEmail(recipient, subject, body);
        verify(producerTemplate).sendBodyAndHeaders(eq("seda:sendEmail"), eq(body), eq(expectedHeaders));
    }
}