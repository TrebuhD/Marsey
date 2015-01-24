package pl.edu.uam.rest.marsey.exceptions;

public class ExceptionMessage {
    private String message;
    private String userMessage;

    public ExceptionMessage(String message, String userMessage) {
        this.message = message;
        this.userMessage = userMessage;
    }

    public String getMessage() {
        return message;
    }

    public String getUserMessage() {
        return userMessage;
    }

}