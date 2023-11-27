package com.quoter.quoter.dto;

public class AuthResponse {
    private final String jet;

    public AuthResponse(String jet) {
        this.jet = jet;
    }

    public String getJet() {
        return jet;
    }
}
