//package pl.edu.uam.rest.marsey.resources;
//
//import com.wordnik.swagger.annotations.Api;
//import pl.edu.uam.rest.marsey.db.MarseyDatabase;
//import pl.edu.uam.rest.marsey.db.MemoryDB;
//
//import javax.ws.rs.Path;
//
//@Path("/memory/candidates")
//@Api(value = "/memory/candidates", description = "candidate operations for memory DB")
//public class MemoryCandidatesResource extends AbstractCandidatesResource {
//    private static final MarseyDatabase db = new MemoryDB();
//
//    @Override
//    protected MarseyDatabase getDatabase() {
//        return db;
//    }
//}
