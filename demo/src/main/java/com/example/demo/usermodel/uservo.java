package com.example.demo.usermodel;

import java.security.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;



@Entity
@Table(name="members")
public class uservo {

    @Id
    @Column(name="id",unique =true)
    private String id;

    @Column(name="pwd")
    private String pwd;

    @Column(name="name")
    private String name;

    @Column(name="email")
    private String email;


    
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
  
   
}
