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
    private Address address;
    private int age;
    private int height;

    public Candidate(CandidateBuilder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.surname = builder.surname;
        this.sex = builder.sex;
        this.occupation = builder.occupation;
        this.address = builder.address;
        this.age = builder.age;
        this.height = builder.height;
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

    @ApiModelProperty(value = "Address")
    public Address getAddress() {
        return address;
    }

    public static class CandidateBuilder{
        private String id;
        private String name;
        private String surname;
        private String sex;
        private String occupation;
        private Address address;
        private int age;
        private int height;

        public CandidateBuilder() {
        }
        
        public CandidateBuilder id(String id) {
            this.id = id;
            return this;
        }
        
        public CandidateBuilder name(String name) {
            this.name = name;
            return this;
        }
        
        public CandidateBuilder surname(String surname) {
            this.surname = surname;
            return this;
        }

        public CandidateBuilder sex(String sex) {
            this.sex = sex;
            return this;
        }

        public CandidateBuilder occupation(String occupation) {
            this.occupation = occupation;
            return this;
        }

        public CandidateBuilder address(Address address) {
            this.address = address;
            return this;
        }

        public CandidateBuilder age(int age) {
            this.age = age;
            return this;
        }

        public CandidateBuilder height(int height) {
            this.height = height;
            return this;
        }

        public Candidate build() {
            return new Candidate(this);
        }
    }
}