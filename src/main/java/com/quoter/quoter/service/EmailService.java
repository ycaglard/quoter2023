package com.quoter.quoter.service;

public interface EmailService {

    void send(String to, String body);
    String buildEmail(String name, String link);
}
