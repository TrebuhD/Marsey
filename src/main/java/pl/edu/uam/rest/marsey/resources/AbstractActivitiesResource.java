package pl.edu.uam.rest.marsey.resources;

import com.wordnik.swagger.annotations.ApiOperation;
import org.apache.log4j.Logger;
import pl.edu.uam.rest.marsey.db.MarseyDatabase;
import pl.edu.uam.rest.marsey.model.Activity;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.Collection;

public abstract class AbstractActivitiesResource {
    
    private static final Logger logger = Logger.getRootLogger();
    protected abstract MarseyDatabase getDatabase();
    
    @Context
    private UriInfo uriInfo;
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Get activity list", notes = "List all activities", response = Activity.class,
            responseContainer = "LIST", position = 1)
    public Collection<Activity> list() { return getDatabase().getActivities(); }
    
    @POST
    @ApiOperation(value = "Register an activity", notes = "Create a new activity",
            response = Activity.class, position = 2)
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createActivity(Activity activity) {
        Activity dbActivity = new Activity("", activity.getType(), activity.getDescription(), activity.getDate());
        
        Activity createdActivity = getDatabase().createActivity(dbActivity);
        
        return Response.created(URI.create(uriInfo.getPath() + "/" + createdActivity.getId()))
                .entity(createdActivity).build();
    }
}
