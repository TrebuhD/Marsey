package pl.edu.uam.rest.marsey.model;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

import java.util.HashSet;
import java.util.Set;


@ApiModel(value = "Candidate")
public class Candidate {
    private String id;
    private String name;
    private String surname;
    private String sex;
    private String occupation;
    private Integer age;
    private Integer height;
    private Integer weight;
    private Float astroFitness;
    
    private Set<Activity> activities = new HashSet<>(0);
    
    public Candidate() {}
    
    public Candidate(String id, String name, String surname, String sex, String occupation,
                     Integer age, Integer height, Integer weight, Float astroFitness) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.sex = sex;
        this.occupation = occupation;
        this.age = age;
        this.height = height;
        this.weight = weight;
        this.astroFitness = astroFitness;
    }
    
    @ApiModelProperty(value = "Candidate id", required = true)
    public String getId() {
        return id;
    }

    @ApiModelProperty(value = "Surname", required = true)
    public String getSurname() {
        return surname;
    }

    @ApiModelProperty(value = "Name", required = true)
    public String getName() {
        return name;
    }

    @ApiModelProperty(value = "Occupation", required = false)
    public String getOccupation() {
        return occupation;
    }

    @ApiModelProperty(value = "Sex", required = false)
    public String getSex() {
        return sex;
    }

    @ApiModelProperty(value = "Height in centimetres", required = false)
    public Integer getHeight() {
        return height;
    }
    
    @ApiModelProperty(value = "Weight in kilograms", required = false)
    public Integer getWeight() { return weight; }

    @ApiModelProperty(value = "Age", required = false)
    public Integer getAge() {
        return age;
    }
    
    @ApiModelProperty(value = "A value between 0 and 1, meaning how well adjusted for a mars mission the candidate is",
            required = false)
    public Float getAstroFitness() { return astroFitness; }

    @ApiModelProperty(value = "Candidate activities")
    public Set getActivities() {
        return activities;
    }
    public void setId(String id) {
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

//    public void setAddress(Address address) {
//        this.address = address;
//    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setAstroFitness(Float astroFitness) {
        this.astroFitness = astroFitness;
    }

    public void setActivities(Set activities) {
        this.activities = activities;
    }
}