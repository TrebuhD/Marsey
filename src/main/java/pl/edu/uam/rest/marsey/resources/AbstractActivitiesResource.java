package pl.edu.uam.rest.marsey.resources;

import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.log4j.Logger;
import pl.edu.uam.rest.marsey.db.MarseyDatabase;
import pl.edu.uam.rest.marsey.exceptions.MyNotFoundException;
import pl.edu.uam.rest.marsey.exceptions.NumericException;
import pl.edu.uam.rest.marsey.model.Activity;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.net.URI;
import java.util.Collection;

@PermitAll
public abstract class AbstractActivitiesResource {

    private static final Logger logger = Logger.getRootLogger();

    protected abstract MarseyDatabase getDatabase();
    
    @Context
    private UriInfo uriInfo;

    @GET
    @RolesAllowed("admin")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Get activity list", notes = "List all activities", response = Activity.class,
            responseContainer = "LIST", position = 1)
    public Collection<Activity> list() {
         return getDatabase().getActivities();
    }

    @RolesAllowed("admin")
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

    @RolesAllowed("admin")
    @GET
    @Path("/{activityId}")
    @ApiOperation(value = "Find activity by id", response = Activity.class, position = 3)
    @Produces(MediaType.APPLICATION_JSON)
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Invalid ID supplied"),
            @ApiResponse(code = 404, message = "Activity not found")
    })
    public Activity getActivity(@PathParam("activityId") String activityId) throws Exception {
        Activity activity = getDatabase().getActivity(activityId);

        if (!NumberUtils.isDigits(activityId) || Integer.valueOf(activityId) < 0) {
            logger.error("Invalid activity id provided");
            throw new NumericException("Invalid id", "Podano nieprawidłowe id");
        }

        if (activityId.equals("db")) {
            logger.error("Database error");
            throw new Exception("Database error");
        }
        
        if (activity == null) {
            logger.error("Activity not found");
            throw new MyNotFoundException("Activity not found", "Nie odnaleziono activity o id " + activityId);
        }
        return activity;
    }
    
    @RolesAllowed("admin")
    @PUT
    @Path("/{activityId}")
    @ApiOperation(value = "Update activity", notes = "Update activity info", response = Activity.class, position = 4)
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Invalid ID supplied"),
            @ApiResponse(code = 404, message = "Activity not found")
    })
    public Response updateActivity(@PathParam("activityId") String activityId, Activity activity) {
        logger.info("PUTting activity " + activity);

        if (!NumberUtils.isDigits(activityId) || Integer.valueOf(activityId) < 0) {
            logger.error("Invalid activity id provided");
            throw new NumericException("Invalid id", "Podano nieprawidłowe id");
        }
        
        Activity activityFromDB = getDatabase().getActivity(activityId);
        if (activityFromDB == null) {
            logger.error("Activity not found");
            throw new MyNotFoundException("Activity not found", "Nie odnaleziono activity o id " + activityId);
        }
        
        Activity newActivity = getDatabase().updateActivity(activityId, activity);
        
        return Response.ok(newActivity).build();
    }
    
    @RolesAllowed("admin")
    @DELETE
    @Path("/{activityId}")
    @ApiOperation(value = "Delete an activity", notes = "Delete an activity", position = 5)
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Invalid ID supplied"),
            @ApiResponse(code = 404, message = "Candidate not found")
    })
    public Response deleteActivity(@PathParam("activityId") String activityId) {

        if (!NumberUtils.isDigits(activityId) || Integer.valueOf(activityId) < 0) {
            logger.error("Invalid activity id provided");
            throw new NumericException("Invalid id", "Podano nieprawidłowe id");
        }
        
        Activity activityFromDb = getDatabase().getActivity(activityId);
        if (activityFromDb == null ) {
            logger.error("Activity not found");
            throw new MyNotFoundException("Activity not found", "Nie odnaleziono activity o id " + activityId);
        }
        
        logger.info("Deleting activity: {}" + activityFromDb);
        getDatabase().deleteActivity(activityId);
        return Response.noContent().build();
    }

}