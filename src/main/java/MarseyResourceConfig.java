import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;
import pl.edu.uam.rest.marsey.resources.ActivitiesResource;

public class MarseyResourceConfig extends ResourceConfig {
    public MarseyResourceConfig() {
        super(ActivitiesResource.class);
        register(RolesAllowedDynamicFeature.class);
    }
}
