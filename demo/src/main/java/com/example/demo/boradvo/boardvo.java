package com.example.demo.boradvo;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;


@Entity
@Table(name="board")
public class boardvo {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//프로젝트에 연결된 db의 넘버링 전략 따라감 ex auto_increment처럼
    @Column(name="bid")
    private int bid;

    @Column(nullable = false, name="email") 
    private String email;

    @Column(nullable = false,name="title") 
    private String title;

    @Column(nullable = false,name="content") 
    private String content;

    @ColumnDefault("0")
    @Column(nullable = false,name="hit") 
    private int hit;

    @CreationTimestamp
    private Timestamp created;

 public int getbid()
 {
     return this.bid;
 }
 public void setemail(String email)
 {
     this.email=email;
 }
 public String getemail()
 {
     return this.email;
 }
 public void settitle(String title)
 {
     this.title=title;
 }
 public String gettitle()
 {
     return this.title;
 }
 public void setcontent(String content)
 {
     this.content=content;
 }
 public String getcontent()
 {
     return this.content;
 }
 public void setcreated(Timestamp created)
 {
     this.created=created;
 }
 public Timestamp getcreated()
 {
     return this.created;
 }
}
