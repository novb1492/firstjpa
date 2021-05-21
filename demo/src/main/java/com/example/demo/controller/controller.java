package com.example.demo.controller;



import java.util.List;

import javax.servlet.http.HttpSession;
import com.example.demo.boarddao.boarddao;
import com.example.demo.boradvo.boardvo;
import com.example.demo.commentdao.commentdao;
import com.example.demo.commentvo.commentvo;
import com.example.demo.config.security;
import com.example.demo.userdao.userdao;
import com.example.demo.uservo.uservo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
    @Autowired
    private security security;

    private final int commentpaging=3;

    @GetMapping("/auth/joinpage")
    public String join() {
        return "joinpage";
    }
    @GetMapping("/auth/loginpage")
    public String loginpage() {
        return "loginpage";
    }
    @PostMapping("/auth/joinprocess")
    public String  joinprocess(uservo user) {   
        try {
        BCryptPasswordEncoder encoder=security.encodepwd();
        String hashpwd=encoder.encode(user.getpwd());
        user.setpwd(hashpwd);
        userdao.save(user);
        return "loginpage";
       
        } catch (DataAccessException e) {
         e.printStackTrace();
        }
        return "joinpage";
    }
    @GetMapping("/auth/index")
    public String index(HttpSession session)   
    {
        return "index";
    }
    @GetMapping("/auth/boardlist")
    public String boardlist(HttpSession session,Model model,@RequestParam(value="page", defaultValue = "1") int pageNum,uservo uservo) {
        try {
            Object principal=SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            UserDetails userDetails=(UserDetails)principal;
            String email=userDetails.getUsername();
            session.setAttribute("email", email);////////////////////////////////이렇게 4줄이 시큐리티 값을 꺼내오는 것이다 한참 고생했다 ㅠㅠ 20210520
        } catch (Exception e) {///////////아 이럴때 트라이 캐치로 감싸면 잘넘어가는구나 
            e.printStackTrace();
        }
        Page<boardvo>array=boarddao.findAll(PageRequest.of(pageNum-1, 3,Sort.by(Sort.Direction.DESC, "bid")));///이한줄짜리 코드가 엄청 소중해서 계속본다 20210517
        model.addAttribute("pages", array.getTotalPages());
        model.addAttribute("array", array);
        return "boardlist";
    }
    @GetMapping("/writearticle")
    public String writeartticle(HttpSession session) {
        return "writearticle";   
    }
    @GetMapping("/auth/content")
    public String content(@RequestParam("bid")int bid,Model model,@RequestParam(value="page", defaultValue = "1") int currentpage) {
        try {
            boardvo vo=boarddao.findById(bid).orElseThrow();
        int count=commentdao.findallcountbyid(bid);
        int totalpages=count/commentpaging;
        if(count%commentpaging>0)
        {
            totalpages++;
        }
        List<commentvo>commentarray=null;
        if(totalpages>0)
        {
        int fisrt=(currentpage-1)*commentpaging+1;
        int end=fisrt+commentpaging-1;
        commentarray=commentdao.findByonebyone(bid,fisrt-1,end-fisrt+1);
        }
        else
        {
            currentpage=0;
            totalpages=0;
        }
        System.out.println("count"+count);
        System.out.println("totalpages"+totalpages);

        model.addAttribute("currentpage", currentpage);
        model.addAttribute("lastpage", totalpages);
        model.addAttribute("array", commentarray);
        model.addAttribute("boardvo", vo);
        } catch (Exception e) {
           e.printStackTrace();
        }
        return "content";
    }
    @GetMapping("updatecontent")
    public String updatecontent(HttpSession session,@RequestParam("bid")int bid,Model model) {
        
        boardvo vo=boarddao.findById(bid).orElseThrow(null);
        model.addAttribute("boardvo", vo);
        return "updatecontent";
    }
    @GetMapping("/mypage")
    public String mypage() {
        return "mypage";
    }
    
 

}
