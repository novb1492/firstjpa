package com.example.demo.service;

import java.util.List;

import com.example.demo.boradvo.boardvo;



public interface iboardservice {
  public int getkim(int pageNum) throws Exception;
  public List<boardvo>getarticles() throws Exception;
}
