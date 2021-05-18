package com.example.demo.commentdao;

import com.example.demo.commentvo.commentvo;

import org.springframework.data.jpa.repository.JpaRepository;

public interface commentdao extends JpaRepository<commentvo,Integer> {
    
}
