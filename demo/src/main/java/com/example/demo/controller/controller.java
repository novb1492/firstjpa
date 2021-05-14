package com.example.demo.controller;

import java.util.Optional;

import com.example.demo.usermodel.uservo;
import com.example.demo.userservice.iuser;
import java.util.function.Supplier;
import org.springframework.beans.factory.annotation.Autowired;
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

    @PostMapping("joinprocess")
    public String  joinprocess(uservo user, Model model) {   
        try {
        if(checkemthy(user))
        {
            Optional<uservo> vo=iuser.findById(user.getid());
           if(vo.isEmpty())///일단 학원가기전까지는 이방법이 제일 편리 한거같다 20200514
           {
                iuser.save(user);
                return "loginpage";
           }
           else
           {
            model.addAttribute("return", "sameid");
            return "joinpage";
           }
        }
        } catch (Exception e) {
         e.printStackTrace();
        }
        model.addAttribute("return", "emthy");
        return "joinpage";
    }

    @GetMapping("select")
    public String array()
    {
        uservo vo=iuser.findById("kim").orElseThrow(()->{
            return null;
          });//영속화시키는것
        return vo.getpwd();
    }
    private boolean checkemthy(uservo user)
    {
        if(user.getEmail().isEmpty()||user.getName().isEmpty()||user.getid().isEmpty()||user.getpwd().isEmpty())
        {
            return false;
        }
        return true;
    }
}
