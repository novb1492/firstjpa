package com.example.demo.userservice;

import com.example.demo.usermodel.uservo;


import org.springframework.data.jpa.repository.JpaRepository;

public interface iuser extends JpaRepository<uservo,String> {
    
}

