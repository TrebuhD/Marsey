package pl.edu.uam.rest.marsey.db;

import pl.edu.uam.rest.marsey.entity.CandidateEntity;
import pl.edu.uam.rest.marsey.model.Candidate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class PostgresDB implements MarseyDatabase {

    private static final String HOST = "ec2-54-83-33-196.compute-1.amazonaws.com";
    private static final int PORT = 5432;
    private static final String DATABASE = "daif3niji906fq";
    private static final String USER_NAME = "yxudxfwvbfxmpj";
    private static final String PASSWORD = "FaygqH_kbDar53iGZf2llDBIy4";

    private static EntityManager entityManager;
    
    public static EntityManager getEntityManager() {
        if (entityManager == null) {
            String dbUrl = "jdbc:postgresql://" + HOST + ":" + PORT + "/" + DATABASE;

            Map<String, String> properties = new HashMap<>();
            
            properties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
            properties.put("javax.persistence.jdbc.driver", "org.postgresql.Driver");
            properties.put("hibernate.connection.url", dbUrl);
            properties.put("hibernate.connection.username", USER_NAME);
            properties.put("hibernate.connection.password", PASSWORD);
            properties.put("hibernate.connection.show_sql", "true");
            properties.put("hibernate.connection.format_sql", "true");
            
            properties.put("hibernate.temp.use_jdbc_metadata_defaults", "false");
            properties.put("hibernate.gbm2ddl.auto", "update");

            EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("myUnit", properties);
            entityManager = entityManagerFactory.createEntityManager();
        }
        
        return entityManager;
    }
    
    @Override
    public Candidate getCandidate(String sid) {
        Long id = null;
        
        try {
            id = Long.valueOf(sid);
        } catch (NumberFormatException e) {
            return null;
        }
        
        CandidateEntity candidateEntity = getEntityManager()
                .find(CandidateEntity.class, id);
        
        if (candidateEntity != null) {
            return buildCandidateResponse(candidateEntity);
        }
        
        return null;
    }

    @Override
    public Candidate createCandidate(Candidate candidate) {
        return null;
    }

    @Override
    public Collection<Candidate> getCandidates() {
        return null;
    }

    @Override
    public Candidate updateCandidate(String candidateId, Candidate Candidate) {
        return null;
    }
    
    private Candidate buildCandidateResponse(CandidateEntity candidateEntity) {
        Candidate.CandidateBuilder candidateBuilder = new Candidate.CandidateBuilder();
        
        candidateBuilder.id(candidateEntity.getId().toString())
                .name(candidateEntity.getName())
                .surname(candidateEntity.getSurname())
                .age(candidateEntity.getAge())
                .height(candidateEntity.getHeight())
                .occupation(candidateEntity.getOccupation())                
                .sex(candidateEntity.getSex());
        // TODO : address.
        
        return new Candidate(candidateBuilder);
    }
    
    private CandidateEntity buildCandidateIdentity(Candidate candidate, boolean active) {
        return new CandidateEntity(candidate.getName(), candidate.getSurname(), candidate.getSex(),
                candidate.getOccupation(), candidate.getHeight(), candidate.getAge(), active);
        
    }
}