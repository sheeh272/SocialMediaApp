package com.example.demo.api;

import com.example.demo.model.JWTInfo;
import com.example.demo.model.User;
import com.example.demo.service.JWTService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequestMapping("api/v1/auth")
@RestController

public class AuthController {

    private final JWTService jwtService;

    private String key = "SecretKeyToJWTsssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss";

    @Autowired
    public AuthController(JWTService jwtService) {
        this.jwtService = jwtService;
    }

    @RequestMapping(method = RequestMethod.GET, params = {"username","passcode"})
    public JWTInfo getJWT(@RequestParam(required = true) String username, @RequestParam(required = true) String passcode) {
        return jwtService.generateToken(key,username,passcode);
    }

    @RequestMapping(method = RequestMethod.GET)
    public UUID getUserId(@RequestHeader("Authorization") String header){
        String jwtToken = header.substring(7);
        return jwtService.getUserId(key,jwtToken);
    }

    @PostMapping
    public boolean validate(@RequestBody JWTInfo jwtinfo) {
        return jwtService.validateToken(key,jwtinfo.getToken());
    }


}
