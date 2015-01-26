package pl.edu.uam.rest.marsey.entity;

import com.google.common.base.MoreObjects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@SuppressWarnings("JpaDataSourceORMInspection")
@Entity
@Table(name = "activities")
@NamedQueries({
        @NamedQuery(name = "activities.findAll", query = "SELECT a from ActivityEntity a")
})
public class ActivityEntity {
    private static final Logger LOGGER = LoggerFactory.getLogger(ActivityEntity.class);

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private Long id;

    @Column(name = "type")
    private String type;

    @Column(name = "description")
    private String description;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date")
    private Date date;

    @ManyToMany(
            cascade = {CascadeType.PERSIST, CascadeType.MERGE },
            fetch = FetchType.LAZY,
            mappedBy = "activities",
            targetEntity = CandidateEntity.class)
    private Set<CandidateEntity> candidates = new HashSet<>(0);

    public ActivityEntity() {
    }
    
    public ActivityEntity(Long id, String type, String description, Date date) {
        this.id = id;
        this.type = type;
        this.description = description;
        this.date = date;
    }

    public ActivityEntity(String type, String description, Date date) {
        this.type = type;
        this.description = description;
        this.date = date;
    }

    @PrePersist
    protected void onCreate() {
        this.date = new Date();
    }

    @PostLoad
    private void postLoad() { LOGGER.info("postLoad: {}", toString()); }

    public String getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    public Long getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("type", type)
                .add("description", description)
                .add("date", date)
                .toString();
    }

    public Set<CandidateEntity> getCandidates() {
        return this.candidates;
    }

    public void setCandidates(Set<CandidateEntity> candidates) {
        this.candidates = candidates;
    }
}
