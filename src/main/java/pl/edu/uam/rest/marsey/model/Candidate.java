package pl.edu.uam.rest.marsey.model;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

@ApiModel(value = "Candidate")
public class Candidate {
    private String id;
    private String name;
    private String surname;
    private String sex;
    private String occupation;
//    private Address address;
    private int age;
    private int height;

    public Candidate(String id, String name, String surname) {
        this.id = id;
        this.name = name;
        this.surname = surname;
    }
    
    public Candidate(String id, String name, String surname, String sex, String occupation, int age, int height) {
        // TODO: address
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.sex = sex;
        this.occupation = occupation;
//        this.address = address;
        this.age = age;
        this.height = height;
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

    @ApiModelProperty(value = "Height", required = false)
    public int getHeight() {
        return height;
    }

    @ApiModelProperty(value = "Age", required = false)
    public int getAge() {
        return age;
    }

//    @ApiModelProperty(value = "Address")
//    public Address getAddress() {
//        return address;
//    }

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
}