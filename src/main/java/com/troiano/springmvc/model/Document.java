package com.troiano.springmvc.model;

import javax.persistence.*;

@Entity
@Table( name = "user_document")
public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idUD;

    @OneToOne(optional = false)
    @JoinColumn(name = "user")
    private User user;

    @Column(name="name", length = 100, nullable =false)
    private String name;

    @Column(name="descr", length = 250, nullable =false)
    private String descr;

    @Column(name="type", length = 100, nullable =false)
    private String type;

    @Lob @Basic(fetch = FetchType.LAZY)
    @Column(name="content", nullable=false)
    private byte[] content;

    public int getIdUD() {
        return idUD;
    }

    public void setIdUD(int idUD) {
        this.idUD = idUD;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }
}
