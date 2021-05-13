package com.example.demo.usermodel;

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
}
