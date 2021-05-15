package com.example.demo.controller;

import org.springframework.web.bind.annotation.RestController;
import java.util.Optional;
import javax.servlet.http.HttpSession;
import com.example.demo.usermodel.uservo;
import com.example.demo.userservice.iuser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
public class restcontroller {
    
    @Autowired
    private iuser iuser;

    @PostMapping("comfirm")
    public String checkemail(uservo user) {

        System.out.println(user.getEmail()+"userid");
        
        Optional<uservo> vo=iuser.findById(user.getEmail());
           if(vo.isEmpty())///일단 학원가기전까지는 이방법이 제일 편리 한거같다 20200514
           {
                return "yes";
           }
        return "no";
    }
    @PostMapping("loginprocess")
    public String loginprocess(HttpSession session,@RequestParam("email")String email,@RequestParam("pwd")String pwd) {
        try {
            uservo user=iuser.findById(email).orElseThrow(()->{
                return null;
              });//영속화시키는것
            if(pwd.equals(user.getpwd()))/////  자바 문자열 비교는 equals 다 왜 까먹고 시간을 이렇게 버렸니 20200515
            {
                session.setAttribute("email",email);
                return "yes";   
            }   
            else{
                return "wrongpwd";
            }    
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "null";
    }
}
