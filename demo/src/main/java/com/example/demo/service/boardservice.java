package com.example.demo.service;



import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.example.demo.boarddao.boarddao;
import com.example.demo.boradvo.boardvo;

import org.springframework.beans.factory.annotation.Autowired;


public class boardservice implements iboardservice {
    
    @Autowired
    private boarddao boarddao;



    @Override
    public int getkim(int pagenum)  {

       return 1;
    }



    @Override
    public List<boardvo> getarticles(HttpSession session,Model model,@RequestParam(value="page", defaultValue = "1") int pageNum) {
        
        List<boardvo>array=boarddao.findAll();
        return array;
    }

   
}
