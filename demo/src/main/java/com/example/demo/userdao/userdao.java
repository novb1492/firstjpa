package com.example.demo.userdao;



import com.example.demo.uservo.uservo;

import org.springframework.data.jpa.repository.JpaRepository;




public interface userdao extends JpaRepository<uservo,String> {
    

}

