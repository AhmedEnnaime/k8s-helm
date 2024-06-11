package com.learning.practice.services.impl;

import com.learning.practice.services.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.flogger.Flogger;
import org.apache.camel.ProducerTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Flogger
public class EmailServiceImpl implements EmailService {

    private final ProducerTemplate producerTemplate;
    @Value("${spring.mail.username}")
    private String from;

    @Override
    public void sendEmail(String recipient, String subject, String body) {
        Map<String, Object> headers = new HashMap<>();
        headers.put("to", recipient);
        headers.put("from", from);
        headers.put("subject", subject);
        headers.put("body", body);
        producerTemplate.sendBodyAndHeaders("seda:sendEmail", body, headers);
    }
}
