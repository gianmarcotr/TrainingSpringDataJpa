package com.troiano.springmvc.model;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table( name = "USERS",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"firstname", "lastname"})})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="user_id", nullable=false, unique=true)
    private int id;

    @NotEmpty
    @Column(name="firstname", length=40, nullable=false)
    private String firstname;

    @NotEmpty
    @Column(name="lastname", length=40, nullable=false)
    private String lastname;

    @Column(name="country", length=40, nullable=true)
    private String country;


    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name="birthDate", nullable = true)
    private Date birthDate;

    @ManyToOne
    @JoinColumn(name="maritalStatus")
    private MaritalStatus maritalStatus;

    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(
            name = "USER_SKILL",
            joinColumns = { @JoinColumn(name = "idU") },
            inverseJoinColumns = { @JoinColumn(name = "idS") }
    )
    List<Skill> skills = new ArrayList<>();

    @OneToOne(mappedBy = "user", fetch = FetchType.EAGER)
    private Document userDocuments;

    public User(String fn, String ln, String country, Date birthDate) {
        this.firstname = fn;
        this.lastname = ln;
        this.country = country;
        this.birthDate = birthDate;

     }

    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public List<Skill> getSkills() {
        return skills;
    }

    public void setSkills(List<Skill> skills) {
        this.skills = skills;
    }

    public Document getUserDocuments() {
        return userDocuments;
    }

    public void setUserDocuments(Document userDocuments) {
        this.userDocuments = userDocuments;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public MaritalStatus getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(MaritalStatus maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

}