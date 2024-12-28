package com.jay.CalendarLog.Models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "communication_methods")
public class CommunicationMethod {
    @Id
    private String name; // Name of the method (e.g., LinkedIn Post)
    private String description; // Description of the method
    private int sequence; // Order in the communication sequence
    private boolean mandatory; // Whether this method is mandatory

    // Constructors, getters, and setters
    public CommunicationMethod(String name, String description, int sequence, boolean mandatory) {
        this.name = name;
        this.description = description;
        this.sequence = sequence;
        this.mandatory = mandatory;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getSequence() {
        return sequence;
    }

    public void setSequence(int sequence) {
        this.sequence = sequence;
    }

    public boolean isMandatory() {
        return mandatory;
    }

    public void setMandatory(boolean mandatory) {
        this.mandatory = mandatory;
    }
}
