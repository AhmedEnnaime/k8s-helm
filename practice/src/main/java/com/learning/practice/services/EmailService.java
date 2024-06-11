package com.learning.practice.services;

public interface EmailService {
    void sendEmail(String recipient, String subject, String body);
}
