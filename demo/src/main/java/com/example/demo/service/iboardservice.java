package com.example.demo.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.example.demo.boradvo.boardvo;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;


@Component 
@Service
public interface iboardservice {
  public int getkim(int pageNum) throws Exception;
  public List<boardvo>getarticles(HttpSession session,Model model,@RequestParam(value="page", defaultValue = "1") int pageNum);
}
