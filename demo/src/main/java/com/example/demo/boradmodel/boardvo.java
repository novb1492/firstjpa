package com.example.demo.boradmodel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="board")
public class boardvo {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//프로젝트에 연결된 db의 넘버링 전략 따라감 ex auto_increment처럼
    @Column(name="id")
    private int id;

    @Column(name="title") 
    private String title;


 public int getid()
 {
     return this.id;
 }
 public void settitle(String title)
 {
     this.title=title;
 }
 public String gettitle()
 {
     return this.title;
 }
}
