package pl.edu.uam.rest.marsey.entity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "activities")
@NamedQueries(
        @NamedQuery(name = "activities.findAll", query = "SELECT a from ActivitiesEntity a")
)

public class ActivitiesEntity {
    private static final Logger LOGGER = LoggerFactory.getLogger(ActivitiesEntity.class);

    @Column(name = "id", unique = true, nullable = false)
    private String activityId;
    
    @ManyToMany(mappedBy = "candidates")
    private List<CandidateEntity> candidates;
    
    
    @Column(name = "type")
    private String type;

    @Id
    public String getActivityId() {
        return activityId;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }
}
