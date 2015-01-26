package pl.edu.uam.rest.marsey.entity;

import javax.persistence.*;
import java.util.Date;

@MappedSuperclass
public abstract class AbstractTimestampEntity {
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created", nullable = false)
    Date created;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated", nullable = false)
    Date updated;
    
    @PrePersist
    protected void onCreate() {
        updated = created = new Date();
    }
    
    @PreUpdate
    protected void onUpdate() {
        updated = new Date();
    }
}
