package com.example.demo.controller;

import org.springframework.web.bind.annotation.RestController;
import java.util.Optional;
import javax.servlet.http.HttpSession;


import com.example.demo.boarddao.boarddao;
import com.example.demo.boradvo.boardvo;
import com.example.demo.commentdao.commentdao;
import com.example.demo.commentvo.commentvo;
import com.example.demo.userdao.userdao;
import com.example.demo.uservo.uservo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
public class restcontroller {
    
    @Autowired
    private userdao userdao;
    @Autowired
    private boarddao boarddao;
    @Autowired
    private commentdao commentdao;

    @PostMapping("/auth/comfirm")
    public String checkemail(@RequestParam("email")String email) {

        System.out.println(email+"userid");
        
        Optional<uservo> vo=userdao.findById(email);
           if(vo.isEmpty())///일단 학원가기전까지는 이방법이 제일 편리 한거같다 20200514
           {
                return "yes";
           }
        return "no";
    }

    /*@PostMapping("/auth/loginprocess")
    public String loginprocess(HttpSession session,@RequestParam("email")String email,@RequestParam("pwd")String pwd) {
        try {
            uservo user=userdao.findById(email).orElseThrow(()->{
                return null;
              });//영속화시키는것
            if(pwd.equals(user.getpwd()))/////  자바 문자열 비교는 equals 다 왜 까먹고 시간을 이렇게 버렸니 20200515
            {
                session.setAttribute("email",user.getEmail());
                session.setAttribute("name",user.getName());
                return "yes";   
            }   
            else{
                return "wrongpwd";
            }    
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "no";
    }*///이거는 시큐리티 혹은 해시없는 버전
    @PostMapping("writearticleprocess")
    public String writearticleprocess(HttpSession session,boardvo vo) {
        
        try {
            vo.setemail((String)session.getAttribute("email"));
            boarddao.save(vo);
            return "yes";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "no";
        
    }
    @Transactional
    @PostMapping("updatecontentprocess")
    public String updatecontentprocess(HttpSession session,boardvo vo,@RequestParam("bid")int bid) {
        try {
            System.out.println(bid+"iddddd");
            boardvo boardvo=boarddao.findById(bid).orElseThrow();
            boardvo.settitle(vo.gettitle());
            boardvo.setcontent(vo.getcontent());
            return "yes";
        } catch (Exception e) {
           e.printStackTrace();
        }
        return "no";
        
    }
    @PostMapping("insertcomment")
    public String insertcomment(HttpSession session,@RequestParam(value = "bid")int bid,@RequestParam("comment")String comment) {
        String r="yes";
        try {
            commentvo commentvo=new commentvo();
            commentvo.setcomment(comment);
            commentvo.setemail((String)session.getAttribute("email"));
            commentvo.setbid(bid);
            System.out.println(bid+comment+(String)session.getAttribute("email"));
            commentdao.save(commentvo);
            r="yes";
            System.out.println(r);
            return r;
            
        } 
        catch (Exception e) {
            e.printStackTrace();
            System.out.println(r);
            return r;
        } 
    }
}
