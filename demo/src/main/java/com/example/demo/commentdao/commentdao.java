package com.example.demo.commentdao;



import java.util.List;

import com.example.demo.commentvo.commentvo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;





public interface commentdao extends JpaRepository<commentvo,Integer> {
   
    @Query(value = "select *from comment  where bid=?1 order by cid desc",nativeQuery = true)
    List<commentvo> findByTitle2(int num);
        ////이거다 네거티브 ㅁㅊ 몇시간쨰 뒤졌는데 진짜...
    ///이거같은데 table이름을 쓰는게 아니라 파일이름을 적어줘야하는거 같다 ㅁㅊ20210518
  
}
