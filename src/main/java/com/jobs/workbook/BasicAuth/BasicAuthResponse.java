package com.jobs.workbook.BasicAuth;

public class BasicAuthResponse {
    String message;

    public BasicAuthResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
