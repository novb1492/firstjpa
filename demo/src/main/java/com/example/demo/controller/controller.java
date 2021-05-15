package com.example.demo.controller;

import com.example.demo.controller.confirm;
import java.util.Optional;

import com.example.demo.usermodel.uservo;
import com.example.demo.userservice.iuser;
import java.util.function.Supplier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    @GetMapping("makesession")
    public String makesession()   
    {
        return "index";
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
