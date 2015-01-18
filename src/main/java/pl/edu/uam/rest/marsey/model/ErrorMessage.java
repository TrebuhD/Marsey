package pl.edu.uam.rest.marsey.model;

public class ErrorMessage {

    private String message;
    private String candidateMessage;

    public ErrorMessage(String message, String userMessage) {
        this.message = message;
        this.candidateMessage = userMessage;
    }

    public String getMessage() {
        return message;
    }

    public String getCandidateMessage() {
        return candidateMessage;
    }
}
