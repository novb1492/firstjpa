package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration//빈등록: 스프링 컨테이너에서 객체에서 관리
@EnableWebSecurity/////필터를 추가해준다
@EnableGlobalMethodSecurity(prePostEnabled = true)//특정 주소 접근을 미리체크 한다  이3개는 셋트임 20210520
public class security extends WebSecurityConfigurerAdapter {
    
    @Bean
    public BCryptPasswordEncoder encodepwd() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception{
       
        http    
            .csrf().disable()///ajax사용하기 위해 토큰은 나중에
            .authorizeRequests()////요청이발생
            .antMatchers("/","/auth/**")////이링크들은 허용된다 
            .permitAll()///허용한다
            .anyRequest()///다른요청들은 
            .authenticated()////막는다
            .and()//그리고
            .formLogin()///로그인창으로 안내해준다
            .loginPage("/auth/loginpage");///내가만든 로그인창으로
    }
}
