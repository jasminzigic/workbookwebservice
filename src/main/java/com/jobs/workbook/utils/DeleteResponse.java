package com.jobs.workbook.utils;

public class DeleteResponse {
    String notificationType;
    String message;
    Number deletedId;

    public String getNotificationType() {
        return notificationType;
    }

    public void setNotificationType(String notificationType) {
        this.notificationType = notificationType;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Number getDeletedId() {
        return deletedId;
    }

    public void setDeletedId(Number deletedJobId) {
        this.deletedId = deletedJobId;
    }

    public DeleteResponse(String notificationType, String message, Number deletedId) {
        this.notificationType = notificationType;
        this.message = message;
        this.deletedId = deletedId;


    }
}
