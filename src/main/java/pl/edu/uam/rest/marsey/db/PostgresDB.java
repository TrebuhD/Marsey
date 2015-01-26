package pl.edu.uam.rest.marsey.db;

import com.google.common.collect.Lists;
import pl.edu.uam.rest.marsey.entity.ActivityEntity;
import pl.edu.uam.rest.marsey.entity.CandidateEntity;
import pl.edu.uam.rest.marsey.model.Activity;
import pl.edu.uam.rest.marsey.model.Candidate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.*;

public class PostgresDB implements MarseyDatabase {

    private static final String HOST = "ec2-54-228-224-40.eu-west-1.compute.amazonaws.com";
    private static final int PORT = 5432;
    private static final String DATABASE = "d986kdq6q8l67f";
    private static final String USER_NAME = "hlcugftcncqqkv";
    private static final String PASSWORD = "t--HqsfLq_2D9o_9fAa3xXuUkg";

    private static EntityManager entityManager;
    
    private static EntityManager getEntityManager() {
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
    
//    CANDIDATE METHODS

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

    private Candidate buildCandidateResponse(CandidateEntity candidateEntity) {
        return new Candidate( String.valueOf(candidateEntity.getId()),
                candidateEntity.getName(),
                candidateEntity.getSurname(),
                candidateEntity.getSex(),
                candidateEntity.getOccupation(),
                candidateEntity.getAge(),
                candidateEntity.getHeight(),
                candidateEntity.getAstroFitness());
    }

    private CandidateEntity buildCandidateEntity(Candidate candidate, boolean active) {
        return new CandidateEntity(candidate.getName(), candidate.getSurname(), candidate.getSex(),
                candidate.getOccupation(), candidate.getHeight(), candidate.getAge(), active, candidate.getAstroFitness());
    }

    private CandidateEntity buildCandidateEntity(Candidate candidate, Long id, boolean active) {
        return new CandidateEntity(id, candidate.getName(), candidate.getSurname(), candidate.getSex(),
                candidate.getOccupation(), candidate.getHeight(), candidate.getAge(), active, candidate.getAstroFitness());
    }
    
//    ACTIVITY METHODS

    @Override
    public Collection<Activity> getActivities() {
        Query query = getEntityManager().createNamedQuery("activities.findAll");
        List<ActivityEntity> resultList = query.getResultList();

        List<Activity> list = Collections.emptyList();

        if (resultList != null && !resultList.isEmpty()) {
            list = Lists.newArrayListWithCapacity(resultList.size());

            for (ActivityEntity activity : resultList) {
                list.add(buildActivityResponse(activity));
            }
        }

        return list;
    }

    @Override
    public Activity getActivity(String sid) {
        Long id;
        
        try {
            id = Long.valueOf(sid);
        } catch (NumberFormatException e) {
            return null;
        }
        
        ActivityEntity activityEntity = getEntityManager()
                .find(ActivityEntity.class, id);
        
        if (activityEntity != null) {
            return buildActivityResponse(activityEntity);
        }
        
        return null;
    }

    // POST
    @Override
    public Activity createActivity(Activity activity) {
        ActivityEntity entity = buildActivityEntity(activity);

        try {
            getEntityManager().getTransaction().begin();

            // Operations that modify the database go here.
            getEntityManager().merge(entity);
            getEntityManager().getTransaction().commit();
        } finally {
            if (getEntityManager().getTransaction().isActive()) {
                getEntityManager().getTransaction().rollback();
            }
        }
        return buildActivityResponse(entity);
    }

    @Override
    public Activity updateActivity(String activityId, Activity activity) {
        Long id;
        
        try {
            id = Long.valueOf(activityId);
        } catch (NumberFormatException e) {
            return null;
        }
        getEntityManager().getTransaction().begin();
        
        ActivityEntity ar = buildActivityEntity(activity, id);
        
        getEntityManager().merge(ar);
        
        getEntityManager().getTransaction().commit();
        
        return buildActivityResponse(ar);
    }

    @Override
    public void deleteActivity(String activityId) {
        getEntityManager().getTransaction().begin();
        getEntityManager().remove(
                entityManager.find(ActivityEntity.class, Long.valueOf(activityId))
        );
        getEntityManager().getTransaction().commit();
    }

    private ActivityEntity buildActivityEntity(Activity activity, Long id) {
        return new ActivityEntity(id, activity.getType(), activity.getDescription(), activity.getDate());
    }

    private ActivityEntity buildActivityEntity(Activity activity) {
        return new ActivityEntity(activity.getType(),
                activity.getDescription(), activity.getDate());
    }

    private Activity buildActivityResponse(ActivityEntity entity) {
        return new Activity(String.valueOf(entity.getId()), entity.getType(),
                entity.getDescription(), entity.getDate());
    }

}