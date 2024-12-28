package com.jay.CalendarLog.Models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Document( collection = "users")
public class User {
    @Id
    public
    String email;
    public
    String password;

    public String getEmail() {
      return this.email;
    }
    public void setEmail(String value) {
      this.email = value;
    }

    public String getPassword() {
      return this.password;
    }
    public void setPassword(String value) {
      this.password = value;
    }
}
