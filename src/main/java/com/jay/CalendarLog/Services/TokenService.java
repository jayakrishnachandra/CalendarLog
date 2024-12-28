package com.jay.CalendarLog.Services;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jay.CalendarLog.Models.Token;
import com.jay.CalendarLog.Repositories.TokenRepository;

@Service
public class TokenService {
    @Autowired
    private TokenRepository tokenRepository;

    public Token save(Token tk) {
        tokenRepository.save(tk);
        return tk;
    }

     // Method to find a token by its token string
    public Optional<Token> findByToken(String tokenString) {
        return tokenRepository.findByToken(tokenString);
    }

    // Optional helper method to check if a token is expired
    public boolean isTokenExpired(Token token) {
        return token.getExpirationTime().isBefore(LocalDateTime.now());
    }

    public Optional<Token> findByEmail(String email) {
        return tokenRepository.findByEmail(email);
    }

    
    
}
