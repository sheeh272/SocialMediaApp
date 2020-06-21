package com.example.demo.dao;

import java.util.UUID;

public interface JWTDao {
    boolean validateUser(String username, String passcode);

    UUID getUserId(String username);
}
