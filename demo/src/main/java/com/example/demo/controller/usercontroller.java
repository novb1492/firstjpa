package com.example.demo.controller;


import java.lang.reflect.Member;
import java.util.List;

import com.example.demo.boardservice.iboard;
import com.example.demo.boradmodel.boardvo;
import com.example.demo.usermodel.uservo;
import com.example.demo.userservice.iuser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class usercontroller {
    
    @Autowired
    private iuser iiuser;


    @GetMapping("select")
    public String array()
    {
        uservo vo=iiuser.findById("kim").orElseThrow(()->{
            return null;
          });//영속화시키는것
        return vo.getpwd();
    }

}
