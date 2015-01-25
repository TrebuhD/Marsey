package pl.edu.uam.rest.marsey.db;

import pl.edu.uam.rest.marsey.model.Activity;
import pl.edu.uam.rest.marsey.model.Candidate;

import java.util.Collection;

public interface MarseyDatabase {
    
    // Candidates
    Candidate getCandidate(String id);

    Candidate createCandidate(Candidate candidate);

    Collection<Candidate> getCandidates();

    Candidate updateCandidate(String candidateId, Candidate Candidate);
    
    void removeCandidate(String candidateId);

    // Activities
    Collection<Activity> getActivities();
    
    Activity getActivity(String id);
    
    Activity createActivity(Activity activity);
    
    Activity updateActivity(String activityId, Activity activity);
    
    void deleteActivity(String activityId);
}