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
            session.setAttribute("email", userservice.getsessionemail());////////////////////////////////????????? 4?????? ???????????? ?????? ???????????? ????????? ?????? ???????????? ?????? 20210520
        } catch (Exception e) {///////////??? ????????? ????????? ????????? ????????? ????????????????????? 
            e.printStackTrace();
        }
       boardservice.getboardlist(pageNum,model);//?????????????????? ?????? ??????????????? ????????? ???????????? ?????????
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
            if(encoder.matches(pwd, pwd2)){////??? ????????? ???????????? ????????? ?????? ????????????????????? ????????? ????????????....?20200521
                System.out.println("success");////??????????????? ??? ???????????? ????????? ???????????? ?????? ????????? ?????? ?????? ???????????? ???????????? ???????????? ?????? 20200521
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
    public String kakaologin(String code,HttpSession session) {/////????????? ????????? ???????????? ????????? ????????? ????????????
        RestTemplate restTemplate=new RestTemplate();
        ////httpheader??????????????????
        HttpHeaders headers=new HttpHeaders();///spring framwork??? ????????????
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
        ///httpbody??????????????????
        MultiValueMap<String,String>params=new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("client_id", "754f0d96ccb805a7da1f4c7fcc5fe1f7");
        params.add("redirect_uri", "http://localhost:8080/auth/kakao/callback");
        params.add("code", code);
        ///header&body????????? ??????
        HttpEntity<MultiValueMap<String,String>> kakaorequest=new HttpEntity<>(params,headers);

        ///http???????????? 
        ResponseEntity<String> responseEntity=restTemplate.exchange(///string ????????? ??????????????? ?????? ?????????
           "https://kauth.kakao.com/oauth/token",//????????????
           HttpMethod.POST,//??????
           kakaorequest,//??????
           String.class //????????????
         );///?????? ???????????????20200521

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
        ////httpheader??????????????????
        HttpHeaders headers2=new HttpHeaders();///spring framwork??? ????????????
        headers2.add("Authorization","Bearer "+oauth.getAccess_token());
        headers2.add("Content-type","application/x-www-form-urlencoded;charset=utf-8");
    
        HttpEntity<MultiValueMap<String,String>> kakaorequest2=new HttpEntity<>(headers2);

        ///http???????????? 
        ResponseEntity<String> responseEntity2=restTemplate2.exchange(///string ????????? ??????????????? ?????? ?????????
           "https://kapi.kakao.com/v2/user/me",//???????????? ??? ???????????? https??? ????????? ?????? 20210521
           HttpMethod.POST,//??????
           kakaorequest2,//??????
           String.class //????????????
         );///?????? ???????????????20200521

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
        String coskey="1111";////??????????????????
        uservo uservo=new uservo();
      
            coskey="1111";////??????????????????
            uservo.setEmail(kakaovo.kakao_account.email);////////////???????????? ??????????????????????????
            uservo.setName(kakaovo.kakao_account.profile.nickname);////??? ??? ???????????? ????????? ?????? ??????????????
            uservo.setpwd(coskey);
           // userdao.save(uservo);////////auto crement ???????????? ??????????????? 
        
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
