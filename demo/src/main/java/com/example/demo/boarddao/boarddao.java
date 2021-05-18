package com.example.demo.boarddao;





import java.util.List;

import com.example.demo.boradvo.boardvo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;



public interface boarddao extends JpaRepository<boardvo,Integer>{

    
    @Query(value = "select *from board order by bid desc",nativeQuery = true)
    List<boardvo> findByallusebid();
        ////이거다 네거티브 ㅁㅊ 몇시간쨰 뒤졌는데 진짜...
    ///이거같은데 table이름을 쓰는게 아니라 파일이름을 적어줘야하는거 같다 ㅁㅊ20210518
}
