package com.example.demo.service;


import org.springframework.stereotype.Service;

import java.util.List;

import com.example.demo.boarddao.boarddao;
import com.example.demo.boradvo.boardvo;

import org.springframework.beans.factory.annotation.Autowired;


@Service
public class boardservice implements iboardservice {
    
    @Autowired
    private boarddao boarddao;



    @Override
    public int getkim(int pagenum)  {

       return 1;
    }



    @Override
    public List<boardvo> getarticles() throws Exception {
        
        List<boardvo>array=boarddao.findByallusebid();
        return array;
    }

   
}
