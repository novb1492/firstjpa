package com.example.demo.commentvo;

import java.sql.Timestamp;

import javax.persistence.Column;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.CreationTimestamp;
import javax.persistence.Entity;



@Entity
@Table(name="comment")
public class commentvo {
    
    @Id////이거 꼭javax.persistence.Id;로해야한다 다른게 들어가있어서 당홨네 persistence 기억하자 20210517
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="cid",nullable = false)
    private int cid;

    @Column(name="bid",nullable = false)
    private int bid;

    @Column(name="email",nullable = false,length = 50)
    private String email;

    @Column(name="comment",nullable = false,length = 50)
    private String comment;

    @CreationTimestamp
    private Timestamp created;


    public void setcomment (String comment) {
        this.comment=comment;
    }
    public String getcomment() {
        return this.comment;
    }
    public void setemail(String email) {
        this.email=email;
    }
    public String getemail() {
        return this.email;
    }
    public void setbid(int bid) {
        this.bid=bid;
    }
    public int getbid() {
        return this.bid;
    }
    
}
