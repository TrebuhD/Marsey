package pl.edu.uam.rest.marsey.exceptions;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class NumericException extends WebApplicationException {
    public NumericException (String message, String userMessage) {
        super(Response.status(Response.Status.BAD_REQUEST)
                .entity(createExceptionMessage(message, userMessage))
                .type(MediaType.APPLICATION_JSON)
                .build());
    }

    private static ExceptionMessage createExceptionMessage(String message, String userMessage) {
        return new ExceptionMessage(message, userMessage);
    }
}