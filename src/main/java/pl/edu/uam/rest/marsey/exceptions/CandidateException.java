package pl.edu.uam.rest.marsey.exceptions;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class CandidateException extends WebApplicationException {
    public CandidateException(String message, String userMessage, String info) {
        super(Response.status(Response.Status.NOT_FOUND)
                .entity(createExceptionMessage(message, userMessage, info))
                .type(MediaType.APPLICATION_JSON)
                .build());
    }

    private static ExceptionMessage createExceptionMessage(String message, String userMessage, String info) {
        return new ExceptionMessage(message, userMessage, info);
    }
}