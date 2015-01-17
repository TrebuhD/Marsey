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
public class CandidatesResource {
    private static final MarseyDatabase database = new PostgresDB();

    public static MarseyDatabase getDatabase() {
        return database;
    }
}
