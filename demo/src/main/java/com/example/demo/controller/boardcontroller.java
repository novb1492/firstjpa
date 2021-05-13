package com.example.demo.controller;

import com.example.demo.boardservice.iboard;
import com.example.demo.boradmodel.boardvo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class boardcontroller {

    @Autowired
    private iboard iboard;

    @GetMapping("selecttwo")
    public String array2()
    {
       boardvo vo=iboard.findById(70).orElseThrow(()->{
            return null;
          });//영속화시키는것
        return vo.gettitle();
    }
}
