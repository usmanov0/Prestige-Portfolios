package com.example.prestigeportfoliocreators.service;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationService {
    private static String appPassword;

    private AuthorizationService() {}

    public void setAppPassword(String appPassword){
        this.appPassword = appPassword;
    }

    public static boolean authorize(HttpServletRequest request){
        if (request == null){
            return false;
        }
        String password = request.getHeader("Authorization");
        return (password != null && !password.isBlank() && password.equals(password));
    }
}
