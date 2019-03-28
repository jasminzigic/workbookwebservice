package com.jobs.workbook.entites.user;

public class UserSingupResponse {
    private boolean success;
    private String description;

    public UserSingupResponse() {
    }

    public UserSingupResponse(boolean success, String description) {
        this.success = success;
        this.description = description;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}