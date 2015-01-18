package pl.edu.uam.rest.marsey.db;

import pl.edu.uam.rest.marsey.model.Candidate;

import java.util.Collection;

public interface MarseyDatabase {
    Candidate getCandidate(String id);

    Candidate createCandidate(Candidate candidate);

    Collection<Candidate> getCandidates();

    Candidate updateCandidate(String candidateId, Candidate Candidate);
    
    void removeCandidate(String candidateId);
}