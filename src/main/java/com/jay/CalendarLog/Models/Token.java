package com.jay.CalendarLog.Models;
import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "token")
public class Token {
     @Id
    String email;
    public String token;
    LocalDateTime expirationTime;

    public Token(String email)
    {
        int TOKEN_EXPIRY_MINUTES = 60*24*7;
        this.email = email;
        this.token = UUID.randomUUID().toString();
        this.expirationTime = LocalDateTime.now().plusMinutes(TOKEN_EXPIRY_MINUTES);
    }

    public String getEmail() {
      return this.email;
    }
    public void setEmail(String value) {
      this.email = value;
    }

    public String getToken() {
      return this.token;
    }
    public void setToken(String value) {
      this.token = value;
    }

    public LocalDateTime getExpirationTime() {
      return this.expirationTime;
    }
    public void setExpirationTime(LocalDateTime value) {
      this.expirationTime = value;
    }
}
