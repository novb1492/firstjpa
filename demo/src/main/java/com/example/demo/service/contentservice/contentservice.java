package com.example.demo.service.contentservice;

import com.example.demo.boarddao.boarddao;
import com.example.demo.boradvo.boardvo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class contentservice {
    @Autowired
    private boarddao boarddao;

    public boardvo getcontent(int bid) {

        boardvo vo=boarddao.findById(bid).orElseThrow();
        vo.sethit(vo.gethit()+1);
        boarddao.save(vo);///조회수 늘려주는 알고리즘 사라져서 다시 만듬!
        return vo;
    }

}
