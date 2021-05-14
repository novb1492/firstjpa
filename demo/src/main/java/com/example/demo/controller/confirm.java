package com.example.demo.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class confirm {
    
    @PostMapping("comfirm")
    public String postMethodName(@RequestParam("userid")String id) {
        System.out.println(id+"userid");
        
        return "yes";
    }
    
}
