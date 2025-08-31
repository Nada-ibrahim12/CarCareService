package org.os.carcareservice.dto;

public class CreateEnquiryDto {
    private String subject;
    private String message;
    private int customerId;
    private int submittedById;

    // Getters and Setters
    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getSubmittedById() {
        return submittedById;
    }

    public void setSubmittedById(int submittedById) {
        this.submittedById = submittedById;
    }
}
