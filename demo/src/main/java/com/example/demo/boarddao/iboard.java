package com.example.demo.boarddao;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import com.example.demo.boradmodel.boardvo;

import org.springframework.data.jpa.repository.JpaRepository;


public interface iboard extends JpaRepository<boardvo,String>{


}
