package com.example.demo.service;
import java.util.UUID;
import java.util.Date;

import com.example.demo.dao.JWTDao;
import com.example.demo.dao.UserDao;
import com.example.demo.model.JWTInfo;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service

public class JWTService {

    private static JWTDao jwtDao;

    @Autowired
    public JWTService(@Qualifier("defultValidation") JWTDao jwtDao) {
        this.jwtDao = jwtDao;
    }

    public static JWTInfo generateToken(String signingKey, String subject, String passcode) {
        if(jwtDao.validateUser(subject,passcode)){
            Date time = new Date(System.currentTimeMillis());
            String token = Jwts.builder().setSubject(subject).claim("passcode",passcode).setIssuedAt(time)
                    .signWith(SignatureAlgorithm.HS256, signingKey).compact();
            return new JWTInfo(token);
        }
        else{
            return null;
        }
    }

    public UUID getUserId(String key, String jwsString){
        String user = getSubject(key, jwsString);
        return jwtDao.getUserId(user);
    }


    public static Jws<Claims> getJws(String key, String jwsString){
        Jws<Claims> jws;
        try {
            jws = Jwts.parserBuilder()  // (1)
                    .setSigningKey(key)         // (2)
                    .build()                    // (3)
                    .parseClaimsJws(jwsString); // (4)

            // we can safely trust the JWT

            return jws;
        }
        catch(JwtException ex){       // (5)
            // we *cannot* use the JWT as intended by its creator
            return null;
        }
    }

    public static boolean validateToken(String key, String jwsString) {
        try{
            Jws<Claims> jws = getJws(key,jwsString);
            return true;
        }
        catch(JwtException ex) {
            return false;
        }
    }

    public static String getSubject(String key, String jwsString){
        try{
            Jws<Claims> jws = getJws(key,jwsString);
            return jws.getBody().getSubject();
        }
        catch(JwtException ex) {
            return null;
        }
    }

    public static String getPasscode(String key, String jwsString){
        try{
            Jws<Claims> jws = getJws(key,jwsString);
            return String.valueOf(jws.getBody().get("passcode"));
        }
        catch(JwtException ex) {
            return null;
        }
    }

}


