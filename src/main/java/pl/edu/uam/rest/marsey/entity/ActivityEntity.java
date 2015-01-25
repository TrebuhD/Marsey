package pl.edu.uam.rest.marsey.entity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;
import java.util.Date;

@SuppressWarnings("JpaDataSourceORMInspection")
@Entity
@Table(name = "activities")
@NamedQueries(
        @NamedQuery(name = "activities.findAll", query = "SELECT a from ActivityEntity a")
)

public class ActivityEntity {
    private static final Logger LOGGER = LoggerFactory.getLogger(ActivityEntity.class);

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true)
    private Long id;

    @Column(name = "type")
    private String type;

    @Column(name = "description")
    private String description;
    
    @Column(name = "date")
    private Date date;
    
//    @ManyToMany(mappedBy = "activity")
//    private List<CandidateEntity> candidates;
    
    public ActivityEntity(Long id, String type, String description, Date date) {
        this.id = id;
        this.type = type;
        this.description = description;
        this.date = date;
    }

    public ActivityEntity() {
    }

    public ActivityEntity(String type, String description, Date date) {
        this.type = type;
        this.description = description;
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    public Date getDate() {
        return date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
