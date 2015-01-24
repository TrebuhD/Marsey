package pl.edu.uam.rest.marsey.resources;

import com.wordnik.swagger.annotations.Api;
import pl.edu.uam.rest.marsey.db.MarseyDatabase;
import pl.edu.uam.rest.marsey.db.PostgresDB;

import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 * Candidates resource for PostgreSQL.
 */

@Path("/candidates")
@Api(value = "Candidates", description = "Candidate operations")
@Produces({"application/json", "application/xml"})
public class CandidatesResource extends AbstractCandidatesResource{
    private static final MarseyDatabase database = new PostgresDB();

    protected MarseyDatabase getDatabase() {
        return database;
    }
}
