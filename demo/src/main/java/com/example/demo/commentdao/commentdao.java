package com.example.demo.commentdao;



import java.util.List;
import com.example.demo.commentvo.commentvo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;





public interface commentdao extends JpaRepository<commentvo,Integer> {
   
    @Query("select comment from commentvo where cid=1")///이거같은데 table이름을 쓰는게 아니라 파일이름을 적어줘야하는거 같다 ㅁㅊ20210518
    public String findByTitle2();
  
}
