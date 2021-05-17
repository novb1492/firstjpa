package com.example.demo.commentvo;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.Id;

public class commentvo {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="cid")
    private int cid;

    @Column(name="bid")
    private int bid;

    @Column(name="email")
    private String email;

    @Column(name="comment")
    private String comment;

    @CreationTimestamp
    private Timestamp created;
    
}
