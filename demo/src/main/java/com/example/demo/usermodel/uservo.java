package com.example.demo.usermodel;

import java.security.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name="members")
public class uservo {

    @Id
    @Column(name="id")
    private String id;

    @Column(name="pwd")
    private String pwd;

    @Column(name="name")
    private String name;

    @Column(name="email")
    private String email;

    @Column(name="created")
    private Timestamp created;
    
    public void setid(String id)
    {
        this.id=id;
    }
    public String getid()
    {
        return this.id;
    }
    public void setpwd(String pwd)
    {
        this.pwd=pwd;
    }
    public String getpwd()
    {
        return this.pwd;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getEmail() {
        return email;
    }
    public Timestamp getCreated() {
        return created;
    }
   
}
