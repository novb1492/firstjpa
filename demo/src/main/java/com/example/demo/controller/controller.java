package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class controller {
    
    @GetMapping("join")
    public String join() {
        return "join";
    }
    @GetMapping("index")
    public String home() {
        return "index.html";
    }
}
