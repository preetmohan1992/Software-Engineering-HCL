package com.glovoapp.backender.http.responses;

import java.util.Date;
import java.util.List;

public class ErrorResponse {
    private Date timestamp;
    private int status;
    private String message;
    private List<String> errors;

    public ErrorResponse(Date timestamp, int status, String message, List<String> errors) {
        this.timestamp = timestamp;
        this.status = status;
        this.message = message;
        this.errors = errors;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public List<String> getErrors() {
        return errors;
    }
}
