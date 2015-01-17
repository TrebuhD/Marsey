package pl.edu.uam.rest.marsey.db;

import pl.edu.uam.rest.marsey.model.Candidate;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class MemoryDB implements MarseyDatabase {
    private static Candidate.CandidateBuilder builder =
            new Candidate.CandidateBuilder().id("").name("roman").surname("romange");
    
    private static Map<String, Candidate> candidateMap = new HashMap<String, Candidate>() {{
        put("0", new Candidate(builder));
    }};
    
    @Override
    public Candidate getCandidate(String id) {
        return candidateMap.get(id);
    }

    @Override
    public Candidate createCandidate(Candidate candidate) {
        String id = Integer.toString(candidateMap.size());
        Candidate value = new Candidate(new Candidate.CandidateBuilder()
                .id(id).name(candidate.getName()).surname(candidate.getSurname()));
        candidateMap.put(id, value);
        return value;
    }

    @Override
    public Collection<Candidate> getCandidates() {
        return candidateMap.values();
    }

    @Override
    public Candidate updateCandidate(String candidateId, Candidate Candidate) {
        return null;
    }
}
