package com.troiano.springmvc.model;

import javax.persistence.*;

@Entity
@Table( name = "MARITAL_STATUS")
public class MaritalStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="idMS", nullable=false, unique=true)
    private Integer idMS;

    @Column(name="nome")
    private String nome;

    public MaritalStatus(String nome) {
        this.nome = nome;
    }

    public MaritalStatus(){}

    public Integer getIdMS() {
        return idMS;
    }

    public void setIdMS(Integer idMS) {
        this.idMS = idMS;
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
