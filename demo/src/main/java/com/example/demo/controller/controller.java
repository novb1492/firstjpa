package com.example.demo.controller;







import java.util.List;

import javax.servlet.http.HttpSession;

import com.example.demo.boarddao.iboard;
import com.example.demo.boradmodel.boardvo;
import com.example.demo.userdao.iuser;
import com.example.demo.usermodel.uservo;

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
    public String boardlist(HttpSession session,Model model,@RequestParam(value="page", defaultValue = "1") int pageNum) {
        model.addAttribute("nums",7);
        Page<boardvo>array=iboard.findAll(PageRequest.of(pageNum-1, 3,Sort.by(Sort.Direction.DESC, "bid")));
        model.addAttribute("titles", array);///와 이거 신세계다 찾는데 한참걸렸지만 쥑인다
        model.addAttribute("pages", array.getTotalPages());/////////////와 totalpages 미쳤다 이거구나 page 진짜 이거 익히는데 앛미10시부터 오후 4시꺼자.. 20210516 뭔지 감이온다!
        return "boardlist";
    }
    @GetMapping("mypage")
    public String mypage(HttpSession session)   
    {
        return "mypage";
    }

}
