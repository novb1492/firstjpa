package com.example.demo.uservo;



import java.sql.Timestamp;

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
    @Column(name="email",length = 30,unique=true,nullable = false)
    private String email;

    @Column(name="pwd",length = 100,nullable = false )
    private String pwd;

    @Column(name="name",length = 20,nullable = false)
    private String name;

    @Column(name="id",nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)///테이블은 여기서 만들고 mysql에서 오토인크리먼트하면된다
    private int id;

    @CreationTimestamp
    private Timestamp created;///이놈때매 테이블은 앞으로 여기서 만들어야겠다

 
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
    public void setid(int id) {
        this.id = id;
    }
    public int getid() {
        return id;
    }
 


   
}
