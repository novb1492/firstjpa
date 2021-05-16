package com.example.demo.service;

import java.util.List;

import javax.transaction.Transactional;

import com.example.demo.boarddao.iboard;
import com.example.demo.boradmodel.boardvo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;



@Service
public class boardservice {
    
    @Autowired
    private iboard iboard;

    @Transactional
    public  Page<boardvo> paging()
    {
        Page<boardvo>array=iboard.findAll(PageRequest.of(1, 3,Sort.by(Sort.Direction.DESC, "bid")));
        return array;
    }
}
