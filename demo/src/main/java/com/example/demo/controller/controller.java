package com.example.demo.controller;



import java.security.Timestamp;

import javax.persistence.Column;
import javax.persistence.Table;
import javax.servlet.http.HttpSession;

import com.example.demo.usermodel.uservo;
import com.example.demo.userservice.iuser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    @GetMapping("loginpage")
    public String loginpage() {
        return "loginpage";
    }
    @PostMapping("joinprocess")
    public String  joinprocess(uservo user, Model model) {   
        try {
        iuser.save(user);
        return "loginpage";
       
        } catch (DataAccessException e) {
         e.printStackTrace();
        }
        return "joinpage";
    }
    @GetMapping("index")
    public String index(HttpSession session)   
    {
        return "index";
    }
    @GetMapping("mypage")
    public String mypage(HttpSession session)   
    {
        return "mypage";
    }
    private boolean checkemthy(uservo user)
    {
        if(user.getEmail().isEmpty()||user.getName().isEmpty()||user.getpwd().isEmpty())
        {
            return false;
        }
        return true;
    }
}
