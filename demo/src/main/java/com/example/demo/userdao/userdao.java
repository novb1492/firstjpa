package com.example.demo.userdao;



import com.example.demo.uservo.uservo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;




public interface userdao extends JpaRepository<uservo,String> {
    
    @Query(value = "select *from members  where email=?1 ",nativeQuery = true)
    uservo findByIdlogin(String email);
}

