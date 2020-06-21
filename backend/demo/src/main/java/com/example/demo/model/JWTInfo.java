package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class JWTInfo {
    private String token;
    private String subject;
    private UUID id;

    public JWTInfo(@JsonProperty("token") String token, @JsonProperty("subject") String subject) {
        this.token = token;
        this.subject = subject;
    }
    public JWTInfo(@JsonProperty("token") String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
