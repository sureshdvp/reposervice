package com.coditas.reposervice.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class ErrorDetails {
    @JsonProperty("timestamp")
    private Date timestamp;

    @JsonProperty("message")
    private String message;

    @JsonProperty("timestamp")
    private String details;

    public ErrorDetails() {
    }

    public ErrorDetails(Date timestamp, String message, String details) {
        super();
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
