package com.example.demo.service;

import java.util.List;

import javax.transaction.Transactional;

import com.example.demo.boarddao.boarddao;
import com.example.demo.boradmodel.boardvo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;




@Service
public class boardservice implements iboardservice {
    
 

    public boardservice(){}

    @Override
    public void getkim() throws Exception {

        System.out.println("pagessssssssssssss");
    }

   
}
