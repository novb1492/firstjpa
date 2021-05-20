package com.example.demo.config.auth;

import com.example.demo.userdao.userdao;
import com.example.demo.uservo.uservo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service//bean
public class principalservice implements UserDetailsService{


    @Autowired
    private userdao userdao;
    //스프링이 로그인 요청을 가로챌때 
    //pwd는 알아서 처리함
    //email 이 db에있나 확인
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //username="kim80800@daum.net";

        
        System.out.println("username"+username);
      uservo uservo =userdao.findByIdlogin(username);
        return new principaldetail(uservo);///////////세션에 유저정보가 저장됨
    }

}
