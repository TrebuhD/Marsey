package pl.edu.uam.rest.marsey.resources;

import com.wordnik.swagger.annotations.ApiOperation;
import org.apache.log4j.Logger;
import pl.edu.uam.rest.marsey.db.MarseyDatabase;
import pl.edu.uam.rest.marsey.exceptions.CandidateException;
import pl.edu.uam.rest.marsey.model.Candidate;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.Collection;

/**
 * Created by hubert on 05.01.15.
 */

public abstract class AbstractCandidatesResource {

    private static final Logger logger = Logger.getRootLogger();
    protected abstract MarseyDatabase getDatabase();

    @Context
    private UriInfo uriInfo;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Get candidate collection", notes = "Get the candidate list", response = Candidate.class,
            responseContainer = "LIST")
    public Collection<Candidate> list() { return getDatabase().getCandidates();}

    @Path("/{candidateId}")
    @ApiOperation(value = "Get candidate by id", response = Candidate.class)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Candidate getCandidate(@PathParam("candidateId") String candidateId) throws Exception {
        Candidate candidate = getDatabase().getCandidate(candidateId);

        if (candidateId.equals("db")) {
            logger.error("Database error");
            throw new Exception("Database error");
        }

        if (candidate == null) {
            logger.error("Candidate not found");
            throw new CandidateException("Candidate not found", "Kandydat nie został znaleziony", "http://docu.pl/errors/user-not-found");
        }

        return candidate;
    }

    @PUT
    @ApiOperation(value = "Update candidate", notes = "update candidate info", response = Candidate.class)
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateCandidate(@PathParam("candidateId") String candidateId, Candidate candidate) {

        Candidate candidateFromDb = getDatabase().getCandidate(candidateId);
        if (candidateFromDb == null) {
            logger.error("Candidate not found");
            throw new CandidateException("Candidate not found",
                    "Kandydat nie został znaleziony",
                    "http://docu.pl/errors/user-not-found");
        }

        Candidate updatedCandidate = getDatabase().updateCandidate(candidateId, candidate);

        return Response.ok(updatedCandidate).build();
    }

}