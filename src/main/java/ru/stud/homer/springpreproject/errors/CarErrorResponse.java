package ru.stud.homer.springpreproject.errors;

public class CarErrorResponse {
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public CarErrorResponse(String message) {
        this.message = message;
    }
}
