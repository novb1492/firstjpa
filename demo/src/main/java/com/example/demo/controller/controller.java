package com.example.demo.controller;


import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import com.example.demo.boarddao.boarddao;
import com.example.demo.boradvo.boardvo;
import com.example.demo.service.boardservice;
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

    private boardservice boardservice=new boardservice();

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
    public String boardlist(HttpSession session,Model model,@RequestParam(value="page", defaultValue = "1") int pageNum) {

        Page<boardvo>array=paging(pageNum);
        model.addAttribute("titles", array);
        model.addAttribute("pages", array.getTotalPages());/////////////와 totalpages 미쳤다 이거구나 page 진짜 이거 익히는데 앛미10시부터 오후 4시꺼자.. 20210516 뭔지 감이온다!
        return "boardlist";
    }
    @GetMapping("content")
    @Transactional
    public String content(HttpSession session,@RequestParam("bid")int bid,Model model) {
        boardvo vo=boarddao.findById(bid).orElseThrow(null);
        vo.sethit(vo.gethit()+1);//신기하네 함수로 빼니까 왜안되는거냐
        System.out.println("hit"+vo.gethit());
        model.addAttribute("array", vo);
        return "content";
    }
    @GetMapping("mypage")
    public String mypage(HttpSession session)   
    {
        return "mypage";
    }
    @GetMapping("writearticle")
    public String writeartticle(HttpSession session) {
        return "writearticle";   
    }
    @GetMapping("updatecontent")
    public String updatecontent(HttpSession session,@RequestParam("bid")int bid,Model model) {
        
        boardvo vo=boarddao.findById(bid).orElseThrow(null);
        model.addAttribute("boardvo", vo);
        return "updatecontent";
    }
    @GetMapping("logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "boardlist";
    }
    private Page<boardvo> paging(int currentpage) {

        return boarddao.findAll(PageRequest.of(currentpage-1, 3,Sort.by(Sort.Direction.DESC, "bid")));///이한줄짜리 코드가 엄청 소중해서 계속본다 20210517
        
    }

 
    
 

}
