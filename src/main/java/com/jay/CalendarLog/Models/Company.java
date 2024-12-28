package com.jay.CalendarLog.Models;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "companies")
public class Company {
    @Id
   // private String id; // Unique identifier
    private String name; // Name of the company
    private String location; // Physical or operational location
    private String linkedInProfile; // URL to LinkedIn profile
    private List<String> emails; // List of email addresses
    private List<String> phoneNumbers; // List of phone numbers
    private String comments; // Additional notes or information
    private int communicationPeriodicity; // Interval for scheduled communications (in days)

    // Constructors, getters, and setters
    public Company(String name, String location, String linkedInProfile, List<String> emails, List<String> phoneNumbers, String comments, int communicationPeriodicity) {
        this.name = name;
        this.location = location;
        this.linkedInProfile = linkedInProfile;
        this.emails = emails;
        this.phoneNumbers = phoneNumbers;
        this.comments = comments;
        this.communicationPeriodicity = communicationPeriodicity;
    }

    // public String getId() {
    //     return id;
    // }

    // public void setId(String id) {
    //     this.id = id;
    // }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLinkedInProfile() {
        return linkedInProfile;
    }

    public void setLinkedInProfile(String linkedInProfile) {
        this.linkedInProfile = linkedInProfile;
    }

    public List<String> getEmails() {
        return emails;
    }

    public void setEmails(List<String> emails) {
        this.emails = emails;
    }

    public List<String> getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setPhoneNumbers(List<String> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public int getCommunicationPeriodicity() {
        return communicationPeriodicity;
    }

    public void setCommunicationPeriodicity(int communicationPeriodicity) {
        this.communicationPeriodicity = communicationPeriodicity;
    }
}
