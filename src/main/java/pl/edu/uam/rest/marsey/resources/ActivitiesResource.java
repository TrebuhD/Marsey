package pl.edu.uam.rest.marsey.resources;

import com.wordnik.swagger.annotations.Api;
import pl.edu.uam.rest.marsey.db.MarseyDatabase;
import pl.edu.uam.rest.marsey.db.PostgresDB;

import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("/activities")
@Api(value = "Activities", description = "Activities performed by candidates")
@Produces({"application/json", "application/xml"})
public class ActivitiesResource extends AbstractActivitiesResource {
    private static final MarseyDatabase database = new PostgresDB();
    
    protected MarseyDatabase getDatabase() { return database; }
}
