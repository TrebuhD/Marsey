package pl.edu.uam.rest.marsey.resources;

import com.wordnik.swagger.annotations.Api;
import pl.edu.uam.rest.marsey.db.MarseyDatabase;
import pl.edu.uam.rest.marsey.db.PostgresDB;

import javax.ws.rs.Path;

/**
 * Candidates resource for PostgreSQL.
 */

@Path("/candidates")
@Api(value = "/candidates", description = "Candidate operations")
public class CandidatesResource extends AbstractCandidatesResource{
    private static final MarseyDatabase database = new PostgresDB();

    protected MarseyDatabase getDatabase() {
        return database;
    }
}
