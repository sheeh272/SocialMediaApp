package com.example.demo;

import com.example.demo.service.JWTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {
    private String key = "SecretKeyToJWTsssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss";

    @Autowired
    private JWTService JWTservice;

    public static String getFullURL(HttpServletRequest request) {
        StringBuilder requestURL = new StringBuilder(request.getRequestURL().toString());
        String queryString = request.getQueryString();

        if (queryString == null) {
            return requestURL.toString();
        } else {
            return requestURL.append('?').append(queryString).toString();
        }
    }

    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        //String originalUri = String.valueOf(request.getAttribute(RequestDispatcher.FORWARD_REQUEST_URI));
        //System.out.println(getFullURL(request));

        final String requestTokenHeader = request.getHeader("Authorization");
        //System.out.println(requestTokenHeader);
        if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
            String jwtToken = requestTokenHeader.substring(7);
            //System.out.println(jwtToken);
            //System.out.println(JWTService.validateToken(key,jwtToken));
            if(JWTService.validateToken(key,jwtToken)){
                String user = JWTService.getSubject(key,jwtToken);
                //System.out.println(user);
                String passcode = JWTService.getPasscode(key,jwtToken);
                //System.out.println(passcode);
                List<GrantedAuthority> grantedAuths = new ArrayList<>();
                grantedAuths.add(new SimpleGrantedAuthority("ROLE_USER"));
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                        user, passcode,grantedAuths);
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }
        chain.doFilter(request, response);
    }
}
