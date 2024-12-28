package com.jay.CalendarLog.Models;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Document(collection = "communication_logs")
public class CommunicationLog {
    @Id
    private String email; // Unique identifier
    private String companyName;
    private String communicationMethodName; 
    
    private LocalDate communicationDate; // Date of the communication
    private String notes; // Additional comments or notes

    // Constructors, getters, and setters
    public CommunicationLog(String companyName, String communicationMethodName, LocalDate communicationDate, String notes) {
        this.companyName = companyName;
        this.communicationMethodName = communicationMethodName;
        this.communicationDate = communicationDate;
        this.notes = notes;
    }

    public LocalDate getCommunicationDate() {
        return communicationDate;
    }

    public void setCommunicationDate(LocalDate communicationDate) {
        this.communicationDate = communicationDate;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCompanyName() {
      return this.companyName;
    }
    public void setCompanyName(String value) {
      this.companyName = value;
    }

    public String getCommunicationMethodName() {
      return this.communicationMethodName;
    }
    public void setCommunicationMethodName(String value) {
      this.communicationMethodName = value;
    }
}
