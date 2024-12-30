package com.jay.CalendarLog.Models;
import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Document(collection = "communication_logs")
public class CommunicationLog {

  @Id
  private String logId; // Unique identifier for each log
  private String email; // Email associated with the log
  private String companyName;
  private String communicationMethodName;
  private LocalDate communicationDate; // Date of the communication
  private String notes; // Additional comments or notes
  private boolean completed; // Status of the communication

  // Constructors, getters, and setters
  public CommunicationLog(String email, String companyName, String communicationMethodName, LocalDate communicationDate, String notes, boolean completed) {
      this.logId = java.util.UUID.randomUUID().toString(); // Generate unique ID
      this.email = email;
      this.companyName = companyName;
      this.communicationMethodName = communicationMethodName;
      this.communicationDate = communicationDate;
      this.notes = notes;
      this.completed = completed;
  }

    public String getLogId() {
      return this.logId;
    }
    public void setLogId(String value) {
      this.logId = value;
    }

    public String getEmail() {
      return this.email;
    }
    public void setEmail(String value) {
      this.email = value;
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

    public LocalDate getCommunicationDate() {
      return this.communicationDate;
    }
    public void setCommunicationDate(LocalDate value) {
      this.communicationDate = value;
    }

    public String getNotes() {
      return this.notes;
    }
    public void setNotes(String value) {
      this.notes = value;
    }

    public boolean getCompleted() {
      return this.completed;
    }
    public void setCompleted(boolean value) {
      this.completed = value;
    }
}