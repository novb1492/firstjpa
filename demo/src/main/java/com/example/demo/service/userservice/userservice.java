package com.example.demo.service.userservice;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class userservice {
    
    public String getsessionemail() {
        Object principal=SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            UserDetails userDetails=(UserDetails)principal;
        return userDetails.getUsername();////////////////////////////////이렇게 3줄이 시큐리티 값을 꺼내오는 것이다 한참 고생했다 ㅠㅠ 20210520
        
    }
}
