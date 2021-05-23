package com.example.demo.service.boardservice;

import java.util.List;

import com.example.demo.boarddao.boarddao;
import com.example.demo.boradvo.boardvo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;


@Service
public class boardservice {
    
    @Autowired
    private boarddao boarddao;
    
    public void getboardlist(int pageNum,Model model) {
        Page<boardvo>array =boarddao.findAll(PageRequest.of(pageNum-1, 3,Sort.by(Sort.Direction.DESC, "bid")));///이한줄짜리 코드가 엄청 소중해서 계속본다 20210517
        System.out.println(array);///// 진짜 페이징이 이렇게 편하게된다
        model.addAttribute("pages", array.getTotalPages());
        model.addAttribute("array", array);
      
    }

    public String getsessionemail() {
        Object principal=SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            UserDetails userDetails=(UserDetails)principal;
        return userDetails.getUsername();
        
    }
}
