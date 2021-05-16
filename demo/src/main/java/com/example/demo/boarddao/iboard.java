package com.example.demo.boarddao;



import java.util.List;

import com.example.demo.boradmodel.boardvo;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface iboard extends JpaRepository<boardvo,String>{

   


}
