package pl.edu.uam.rest.marsey.db;

import com.google.common.collect.Lists;
import pl.edu.uam.rest.marsey.entity.CandidateEntity;
import pl.edu.uam.rest.marsey.model.Candidate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.*;

public class PostgresDB implements MarseyDatabase {

    private static final String HOST = "ec2-54-83-33-196.compute-1.amazonaws.com";
    private static final int PORT = 5432;
    private static final String DATABASE = "daif3niji906fq";
    private static final String USER_NAME = "yxudxfwvbfxmpj";
    private static final String PASSWORD = "FaygqH_kbDar53iGZf2llDBIy4";

    private static EntityManager entityManager;
    
    public static EntityManager getEntityManager() {
        if (entityManager == null) {
            String dbUrl = "jdbc:postgresql://" + HOST + ":" + PORT + "/" + DATABASE +
                    "?ssl=true&sslfactory=org.postgresql.ssl.NonValidatingFactory";

            Map<String, String> properties = new HashMap<>();
            
            properties.put("hibernate.connection.requireSSL", "true");
            properties.put("hibernate.connection.verifyServerCertificate", "false");

            properties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
            properties.put("javax.persistence.jdbc.driver", "org.postgresql.Driver");
            properties.put("hibernate.connection.url", dbUrl);
            properties.put("hibernate.connection.username", USER_NAME);
            properties.put("hibernate.connection.password", PASSWORD);
            properties.put("hibernate.connection.show_sql", "true");
            properties.put("hibernate.connection.format_sql", "true");
            
            properties.put("hibernate.temp.use_jdbc_metadata_defaults", "false");
            properties.put("hibernate.hbm2ddl.auto", "update");

            EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("myUnit", properties);
            entityManager = entityManagerFactory.createEntityManager();
        }
        
        return entityManager;
    }
    
    // CANDIDATE METHODS:
    
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
        CandidateEntity entity = buildCandidateEntity(candidate, false);
        
        try {
            getEntityManager().getTransaction().begin();
            
            // Operations that modify the database go here.
            getEntityManager().persist(entity);
            getEntityManager().getTransaction().commit();
        } finally {
            if (getEntityManager().getTransaction().isActive()) {
                getEntityManager().getTransaction().rollback();
            }
        }
        
        return buildCandidateResponse(entity);
    }
    
    @Override
    public Candidate updateCandidate(String candidateId, Candidate candidate) {
        Long id;

        try {
            id = Long.valueOf(candidateId);
        } catch (NumberFormatException e) {
            return null;
        }
        getEntityManager().getTransaction().begin();
        
        CandidateEntity e = buildCandidateEntity(candidate, id, false);
        getEntityManager().merge(e);
        
        getEntityManager().getTransaction().commit();
        
        return buildCandidateResponse(e);
    }

    @Override
    public void removeCandidate(String candidateId) {
        getEntityManager().getTransaction().begin();
        getEntityManager().remove(
                entityManager.find(CandidateEntity.class, Long.valueOf(candidateId))
        );
        getEntityManager().getTransaction().commit();
    }

    @Override
    public Collection<Candidate> getCandidates() {
        Query query = getEntityManager().createNamedQuery("candidates.findAll");
        List<CandidateEntity> resultList = query.getResultList();
        
        List<Candidate> list = Collections.emptyList();
        
        if (resultList != null && !resultList.isEmpty()) {
            list = Lists.newArrayListWithCapacity(resultList.size());
            
            for (CandidateEntity candidate : resultList) {
                list.add(buildCandidateResponse(candidate));
            }
        }
        
        return list;
    }
    

    private Candidate buildCandidateResponse(CandidateEntity candidateEntity) {
        return new Candidate( String.valueOf(candidateEntity.getId()),
                candidateEntity.getName(),
                candidateEntity.getSurname(),
                candidateEntity.getSex(),
                candidateEntity.getOccupation(),
                candidateEntity.getAge(),
                candidateEntity.getHeight());
    }
    
    private CandidateEntity buildCandidateEntity(Candidate candidate, boolean active) {
        return new CandidateEntity(candidate.getName(), candidate.getSurname(), candidate.getSex(),
                candidate.getOccupation(), candidate.getHeight(), candidate.getAge(), active);
    }

    private CandidateEntity buildCandidateEntity(Candidate candidate, Long id, boolean active) {
        return new CandidateEntity(id, candidate.getName(), candidate.getSurname(), candidate.getSex(),
                candidate.getOccupation(), candidate.getHeight(), candidate.getAge(), active);
    }

}