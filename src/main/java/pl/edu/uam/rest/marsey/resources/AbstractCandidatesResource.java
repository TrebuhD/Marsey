package pl.edu.uam.rest.marsey.resources;

import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.log4j.Logger;
import pl.edu.uam.rest.marsey.db.MarseyDatabase;
import pl.edu.uam.rest.marsey.exceptions.CandidateNotFoundException;
import pl.edu.uam.rest.marsey.exceptions.NumericException;
import pl.edu.uam.rest.marsey.model.Candidate;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.Collection;

public abstract class AbstractCandidatesResource {

    private static final Logger logger = Logger.getRootLogger();
    protected abstract MarseyDatabase getDatabase();

    @Context
    private UriInfo uriInfo;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Get candidate collection", notes = "Get the candidate list", response = Candidate.class,
            responseContainer = "LIST", position = 1)
    public Collection<Candidate> list() { return getDatabase().getCandidates(); }
    
    @POST
    @ApiOperation(value = "Create candidate", notes = "Create candidate", response = Candidate.class, position = 2)
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createCandidate(Candidate candidate) {
        Candidate dbCandidate = new Candidate("", candidate.getName(), candidate.getSurname(), candidate.getSex(),
                candidate.getOccupation(), candidate.getAge(), candidate.getHeight(), candidate.getAstroFitness());

        Candidate createdCandidate = getDatabase().createCandidate(dbCandidate);

        return Response.created(URI.create(uriInfo.getPath() + "/" + createdCandidate.getId()))
                .entity(createdCandidate).build();
    }

    @GET
    @Path("/{candidateId}")
    @ApiOperation(value = "Find candidate by id", response = Candidate.class, position = 3)
    @Produces(MediaType.APPLICATION_JSON)
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Invalid ID supplied"),
            @ApiResponse(code = 404, message = "Candidate not found")
    })
    public Candidate getCandidate(@PathParam("candidateId") String candidateId) throws Exception {
        Candidate candidate = getDatabase().getCandidate(candidateId);
        
        logger.info(NumberUtils.isDigits(candidateId));

        if (!NumberUtils.isDigits(candidateId) || Integer.valueOf(candidateId) < 0) {
            logger.error("Invalid id provided.");
            throw new NumericException("Invalid id", "Podano nieprawidłowe id");
        }

        if (candidateId.equals("db")) {
            logger.error("Database error");
            throw new Exception("Database error");
        }

        if (candidate == null) {
            logger.error("Candidate not found");
            throw new CandidateNotFoundException("Candidate not found", "Kandydat nie został znaleziony");
        }

        return candidate;
    }
    

    @PUT
    @Path("/{candidateId}")
    @ApiOperation(value = "Update candidate", notes = "update candidate info", response = Candidate.class, position = 4)
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Invalid ID supplied"),
            @ApiResponse(code = 404, message = "Candidate not found")
    })
    public Response updateCandidate(@PathParam("candidateId") String candidateId, Candidate candidate) {
        int id = Integer.valueOf(candidateId);
        logger.info("PUT candidate" + candidate);

        if (!NumberUtils.isNumber(candidateId) || Integer.valueOf(candidateId) < 0) {
            logger.error("Invalid id provided.");
            throw new NumericException("Invalid id", "Podano nieprawidłowe id");
        }

        Candidate candidateFromDb = getDatabase().getCandidate(candidateId);
        if (candidateFromDb == null) {
            logger.error("Candidate not found");
            throw new CandidateNotFoundException("Candidate not found", "Kandydat nie został znaleziony");
        }

        Candidate updatedCandidate = getDatabase().updateCandidate(candidateId, candidate);
        
        

        return Response.ok(updatedCandidate).build();
    }

    @DELETE
    @Path("/{candidateId}")
    @ApiOperation(value = "Remove a candidate", notes = "Delete a candidate", position = 5)
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Invalid ID supplied"),
            @ApiResponse(code = 404, message = "Candidate not found")
    })
    public Response removeCandidate(@PathParam("candidateId") String candidateId) {

        if (!NumberUtils.isNumber(candidateId) || Integer.valueOf(candidateId) < 0) {
            logger.error("Invalid id provided.");
            throw new NumericException("Invalid id", "Podano nieprawidłowe id");
        }

        Candidate candidateFromDb = getDatabase().getCandidate(candidateId);
        if (candidateFromDb == null) {
            logger.error("Candidate not found");
            throw new CandidateNotFoundException("Candidate not found",
                    "Kandydat nie został znaleziony");
        }

        getDatabase().removeCandidate(candidateId);
        return Response.noContent().build();
    }

}