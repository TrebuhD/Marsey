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
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;
    
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
    
    //indexing of fields for better performance
    private boolean active = false;

    public CandidateEntity(String name, String surname, String sex, String occupation, int height, int age, boolean active) {
        this.name = name;
        this.surname = surname;
        this.sex = sex;
        this.occupation = occupation;
        this.height = height;
        this.age = age;
        this.active = active;
    }

    // Lifecycle methods:
    @PostLoad
    private void postLoad() { LOGGER.info("postLoad: {}", toString());}
    
    public CandidateEntity() {}

    public CandidateEntity(Long id, String firstName, String lastName, String sex,
                           String occupation, Integer height, Integer age, boolean active) {
        this.id = id;
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


    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

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
