package com.example.demo.controller;



import java.util.List;


import javax.servlet.http.HttpSession;
import com.example.demo.boarddao.boarddao;
import com.example.demo.boradvo.boardvo;
import com.example.demo.commentdao.commentdao;
import com.example.demo.commentvo.commentvo;
import com.example.demo.config.security;
import com.example.demo.kakaovo.kakaovo;
import com.example.demo.oauthtoken.oauthtoken;
import com.example.demo.service.boardservice.boardservice;
import com.example.demo.service.contentservice.contentservice;
import com.example.demo.service.userservice.userservice;
import com.example.demo.userdao.userdao;
import com.example.demo.uservo.uservo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;




@Controller
public class controller {
    
    @Autowired
    private userdao userdao;
    @Autowired
    private boarddao boarddao;
    @Autowired
    private security security;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private boardservice boardservice;
    @Autowired
    private contentservice contentservice;
    @Autowired
    private userservice userservice;
    


    @GetMapping("/auth/joinpage")
    public String join() {
        return "joinpage";
    }
    @GetMapping("/auth/loginpage")
    public String loginpage() {
        return "loginpage";
    }
    @PostMapping("/auth/joinprocess")
    public String  joinprocess(uservo user) {   
        try {
        BCryptPasswordEncoder encoder=security.encodepwd();
        String hashpwd=encoder.encode(user.getpwd());
        user.setpwd(hashpwd);
        userdao.save(user);
        return "loginpage";
       
        } catch (DataAccessException e) {
         e.printStackTrace();
        }
        return "joinpage";
    }
    @GetMapping("/auth/index")
    public String index(HttpSession session)   
    {
        return "index";
    }
    @GetMapping("/auth/boardlist")
    public String boardlist(HttpSession session,Model model,@RequestParam(value="page", defaultValue = "1") int pageNum,uservo uservo) {
        try {
            session.setAttribute("email", userservice.getsessionemail());////////////////////////////////이렇게 4줄이 시큐리티 값을 꺼내오는 것이다 한참 고생했다 ㅠㅠ 20210520
        } catch (Exception e) {///////////아 이럴때 트라이 캐치로 감싸면 잘넘어가는구나 
            e.printStackTrace();
        }
       boardservice.getboardlist(pageNum,model);//여기들어가서 보면 신세계이다 페이징 간편하게 처리됨
        return "boardlist";
    }
    @GetMapping("/writearticle")
    public String writeartticle(HttpSession session) {
        return "writearticle";   
    }
    @GetMapping("/auth/content")
    public String content(@RequestParam("bid")int bid,Model model,@RequestParam(value="page", defaultValue = "1") int currentpage) {
        try {
            contentservice.commentpagin(bid, model, currentpage);
        } catch (Exception e) {
           e.printStackTrace();
        }
        return "content";
    }
    @GetMapping("updatecontent")
    public String updatecontent(HttpSession session,@RequestParam("bid")int bid,Model model) {
        
        boardvo vo=boarddao.findById(bid).orElseThrow(null);
        model.addAttribute("boardvo", vo);
        return "updatecontent";
    }
    @GetMapping("mypage")
    public String mypage(Model model) {
      
        return "mypage";
    }
    @GetMapping("updatepwdpage")
    public String updatepwdpage() {
        return "updatepwdpage";
    }
    @PostMapping("updatepwdprocess")
    public String updatepwdprocess(@RequestParam("pwd")String pwd,HttpSession session) {
        String pwd2=userdao.findpwdById((String)session.getAttribute("email"));
        System.out.println(pwd2);
        try {
            BCryptPasswordEncoder encoder=security.encodepwd();
            if(encoder.matches(pwd, pwd2)){////오 이걸로 깔끔하게 비교가 된다 그러면로그인도 이걸로 하겠는데....?20200521
                System.out.println("success");////로그인으로 왜 안되는지 알았다 시큐리티 빈에 등록이 되지 않아 말그대로 권한없이 로그인만 된다 20200521
            }
           else{
            System.out.println("fail");
           }
            } catch (DataAccessException e) {
             e.printStackTrace();
            }
        return "mypage";
    }
    @GetMapping(value="/auth/kakao/callback")
    public String kakaologin(String code,HttpSession session) {/////코드를 받으면 인증성공 그다음 토크을 받아야함
        RestTemplate restTemplate=new RestTemplate();
        ////httpheader오브젝트생성
        HttpHeaders headers=new HttpHeaders();///spring framwork로 선택해라
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
        ///httpbody오브젝트생성
        MultiValueMap<String,String>params=new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("client_id", "754f0d96ccb805a7da1f4c7fcc5fe1f7");
        params.add("redirect_uri", "http://localhost:8080/auth/kakao/callback");
        params.add("code", code);
        ///header&body한곳에 넣기
        HttpEntity<MultiValueMap<String,String>> kakaorequest=new HttpEntity<>(params,headers);

        ///http요청하기 
        ResponseEntity<String> responseEntity=restTemplate.exchange(///string 제너릭 넣으래잖아 제발 준영아
           "https://kauth.kakao.com/oauth/token",//요청주소
           HttpMethod.POST,//방식
           kakaorequest,//객체
           String.class //답변형식
         );///진짜 별개다되네20200521

         ObjectMapper objectMapper=new ObjectMapper();
         oauthtoken oauth=null;
         try {
        oauth=objectMapper.readValue(responseEntity.getBody(),oauthtoken.class);
         } catch (JsonMappingException e) {
           e.printStackTrace();
         }
         catch(JsonProcessingException e){
            e.printStackTrace();
         }
        System.out.println(oauth.getAccess_token());


        RestTemplate restTemplate2=new RestTemplate();
        ////httpheader오브젝트생성
        HttpHeaders headers2=new HttpHeaders();///spring framwork로 선택해라
        headers2.add("Authorization","Bearer "+oauth.getAccess_token());
        headers2.add("Content-type","application/x-www-form-urlencoded;charset=utf-8");
    
        HttpEntity<MultiValueMap<String,String>> kakaorequest2=new HttpEntity<>(headers2);

        ///http요청하기 
        ResponseEntity<String> responseEntity2=restTemplate2.exchange(///string 제너릭 넣으래잖아 제발 준영아
           "https://kapi.kakao.com/v2/user/me",//요청주소 하 카카오는 https다 준영아 제발 20210521
           HttpMethod.POST,//방식
           kakaorequest2,//객체
           String.class //답변형식
         );///진짜 별개다되네20200521

         ObjectMapper objectMapper2=new ObjectMapper();

         kakaovo kakaovo=new kakaovo();
         try {
        kakaovo=objectMapper2.readValue(responseEntity2.getBody(),kakaovo.class);
         } catch (JsonMappingException e) {
           e.printStackTrace();
         }
         catch(JsonProcessingException e){
            e.printStackTrace();
         }
        System.out.println();
        String coskey="1111";////관리잘해야함
        uservo uservo=new uservo();
      
            coskey="1111";////관리잘해야함
            uservo.setEmail(kakaovo.kakao_account.email);////////////맥에서는 이렇게해야한다고??
            uservo.setName(kakaovo.kakao_account.profile.nickname);////엥 왜 맥에서는 이렇게 해야 되는거지??
            uservo.setpwd(coskey);
           // userdao.save(uservo);////////auto crement 자동으로 안들어간다 
        
            try {
                Authentication authentication=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(uservo.getEmail(), coskey));
                SecurityContextHolder.getContext().setAuthentication(authentication);
                session.setAttribute("email", uservo.getEmail());
                System.out.println(uservo.getEmail()+"email");
            } catch (Exception e) {
                e.printStackTrace();
            }
    
        return "redirect:/";
    }
    
 

}
