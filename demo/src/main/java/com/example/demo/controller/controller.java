package com.example.demo.controller;




import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.example.demo.boarddao.iboard;
import com.example.demo.boradmodel.boardvo;
import com.example.demo.userdao.iuser;
import com.example.demo.usermodel.uservo;

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
    @Autowired
    private iboard iboard;

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
    @GetMapping("boardlist")
    public String boardlist(HttpSession session,Model model) {
        model.addAttribute("nums",7);
        model.addAttribute("title", iboard.findAll());
        return "boardlist";
    }
    @GetMapping("mypage")
    public String mypage(HttpSession session)   
    {
        return "mypage";
    }

}
