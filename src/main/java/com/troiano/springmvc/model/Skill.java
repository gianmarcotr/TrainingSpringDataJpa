package com.troiano.springmvc.model;

import com.troiano.springmvc.model.User;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table (name = "skills")
public class Skill {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idS", nullable=false, unique=true)
    private int idS;

    @Column(name = "nome")
    private String nome;

    @ManyToMany(mappedBy = "skills")
    private List<User> users = new ArrayList<>();


    public Skill(int idS) {
        this.idS = idS;
    }

    public Skill(){}

    public int getIdS() {
        return idS;
    }

    public void setIdS(int idS) {
        this.idS = idS;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public String getNome() {
        return nome;

    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
