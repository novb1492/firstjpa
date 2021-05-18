package com.example.demo.controller;


import java.util.List;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import com.example.demo.boarddao.boarddao;
import com.example.demo.boradvo.boardvo;
import com.example.demo.commentdao.commentdao;
import com.example.demo.commentvo.commentvo;
import com.example.demo.service.boardservice;
import com.example.demo.service.iboardservice;
import com.example.demo.userdao.userdao;
import com.example.demo.uservo.uservo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;




@Controller
public class controller {
    
    @Autowired
    private userdao userdao;
    @Autowired
    private boarddao boarddao;
    @Autowired
    private commentdao commentdao;

    @GetMapping("joinpage")
    public String join() {
        return "joinpage";
    }
    @GetMapping("loginpage")
    public String loginpage() {
        return "loginpage";
    }
    @PostMapping("joinprocess")
    public String  joinprocess(uservo user) {   
        try {
        userdao.save(user);
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
    public String boardlist(Model model,@RequestParam(value="page", defaultValue = "1") int pageNum) {
        Page<boardvo>array=boarddao.findAll(PageRequest.of(pageNum-1, 3,Sort.by(Sort.Direction.DESC, "bid")));///이한줄짜리 코드가 엄청 소중해서 계속본다 20210517
       model.addAttribute("pages", array.getTotalPages());
        model.addAttribute("array", array);
        return "boardlist";
    }
    @GetMapping("writearticle")
    public String writeartticle(HttpSession session) {
        return "writearticle";   
    }
    @GetMapping("content")
    public String content(@RequestParam("bid")int bid,Model model) {
        boardvo vo=boarddao.findById(bid).orElseThrow();
        List<commentvo>array=commentdao.findByonebyone(bid);
        model.addAttribute("array", array);
        model.addAttribute("boardvo", vo);
        return "content";
    }
    @GetMapping("updatecontent")
    public String updatecontent(HttpSession session,@RequestParam("bid")int bid,Model model) {
        
        boardvo vo=boarddao.findById(bid).orElseThrow(null);
        model.addAttribute("boardvo", vo);
        return "updatecontent";
    }


 
    
 

}
