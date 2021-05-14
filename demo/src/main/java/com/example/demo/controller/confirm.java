package com.example.demo.controller;

import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

import com.example.demo.usermodel.uservo;
import com.example.demo.userservice.iuser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;



@RestController
public class confirm {
    @Autowired
    private iuser iuser;
    @PostMapping("comfirm")
    public String postMethodName(uservo user) {

        System.out.println(user.getid()+"userid");
        
        Optional<uservo> vo=iuser.findById(user.getid());
           if(vo.isEmpty()&&user.getid()!=null)///일단 학원가기전까지는 이방법이 제일 편리 한거같다 20200514
           {
                return "yes";
           }
        return "no";
    }
    
}
