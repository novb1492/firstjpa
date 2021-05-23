package com.example.demo.service.contentservice;

import java.util.List;

import com.example.demo.boarddao.boarddao;
import com.example.demo.boradvo.boardvo;
import com.example.demo.commentdao.commentdao;
import com.example.demo.commentvo.commentvo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class contentservice {
    @Autowired
    private boarddao boarddao;
    @Autowired
    private commentdao commentdao;
    
    private final int commentpaging=3;

    public boardvo getcontent(int bid) {

        boardvo vo=boarddao.findById(bid).orElseThrow();
        vo.sethit(vo.gethit()+1);
        boarddao.save(vo);///조회수 늘려주는 알고리즘 사라져서 다시 만듬!
        return vo;
    }
    public void commentpagin(int bid,Model model,int currentpage) {
        
        boardvo vo=getcontent(bid); 
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
        
    }

}
