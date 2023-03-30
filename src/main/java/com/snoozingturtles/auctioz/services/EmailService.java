package com.snoozingturtles.auctioz.services;

import jakarta.mail.MessagingException;

public interface EmailService {
    void sendSimpleEmail();
    void sendEmailWithAttachment(String sendTo, String body, String subject, String attachment) throws MessagingException;
}

