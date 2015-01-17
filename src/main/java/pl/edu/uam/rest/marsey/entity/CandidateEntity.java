package pl.edu.uam.rest.marsey.entity;

import com.google.common.base.MoreObjects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;

@Entity
@Table(name = "candidates")
@NamedQueries({
        @NamedQuery(name = "candidates.findAll", query = "SELECT c FROM CandidateEntity c")
})
public class CandidateEntity {
    private static final Logger LOGGER = LoggerFactory.getLogger(CandidateEntity.class);
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    
    @Column(name = "name")
    private String name;
    
    @Column(name = "surname")
    private String surname;
    
    @Column(name = "sex")
    private String sex;

    @Column(name = "occupation")
    private String occupation;
    
    @Column(name = "age")
    private Integer age;
    
    @Column(name = "height")
    private Integer height;
    
    private boolean active = false;
    
    // Lifecycle methods:
    @PostLoad
    private void postLoad() { LOGGER.info("postLoad: {}", toString());}
    
    public CandidateEntity() {}

    public CandidateEntity(String firstName, String lastName, String sex,
                           String occupation, Integer height, Integer age, boolean active) {
        this.name = firstName;
        this.surname = lastName;
        this.sex = sex;
        this.occupation = occupation;
        this.age = age;
        this.height = height;
        this.active = active;
    }
    
    public Long getId() {
        return id;
    }
    
    public String getName() { return name; }
    
    public String getSurname() { return surname; }


    public String getSex() {
        return sex;
    }

    public String getOccupation() {
        return occupation;
    }

    public Integer getAge() {
        return age;
    }

    public Integer getHeight() {
        return height;
    }
    public boolean isActive() { return active; }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("name", name)
                .add("surname", surname)
                .add("sex", sex)
                .add("occupation", occupation)
                .add("age", age)
                .add("height", height)
                .add("active", active)
                .toString();
    }
}
