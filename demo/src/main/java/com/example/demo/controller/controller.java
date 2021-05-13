package com.example.demo.controller;

import com.example.demo.usermodel.uservo;
import com.example.demo.userservice.iuser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class controller {
    
    @Autowired
    private iuser iuser;

    @GetMapping("joinpage")
    public String join() {
        return "joinpage";
    }

    @PostMapping("joinprocess")
    public String  joinprocess(uservo user) {
        try {
            iuser.save(user);
            return "loginpage";
        } catch (Exception e) {
         e.printStackTrace();
        }
        return "joinpage";
    }
}
