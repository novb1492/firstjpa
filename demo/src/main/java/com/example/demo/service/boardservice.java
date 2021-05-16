package com.example.demo.service;

import com.example.demo.boarddao.iboard;
import com.example.demo.boradmodel.boardvo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class boardservice {
    
    @Autowired
    private iboard iboard;

    public Page<boardvo> re(Pageable pageable)
    {
        return iboard.findAll(pageable);
    }
}
