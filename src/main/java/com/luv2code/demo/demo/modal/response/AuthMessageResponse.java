package com.luv2code.demo.demo.modal.response;

public class AuthMessageResponse {
    private Integer status;
    private String message;
    private Long timeStamp;
    private boolean isError;

    public AuthMessageResponse() {
    }

    public AuthMessageResponse(Integer status, String message, Long timeStamp, boolean isError) {
        this.status = status;
        this.message = message;
        this.timeStamp = timeStamp;
        this.isError = isError;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public boolean isError() {
        return isError;
    }

    public void setError(boolean error) {
        isError = error;
    }
}
