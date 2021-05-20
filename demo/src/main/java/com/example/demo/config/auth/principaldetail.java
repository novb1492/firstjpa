package com.example.demo.config.auth;

import java.util.ArrayList;
import java.util.Collection;

import com.example.demo.uservo.uservo;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

//스프링 시큐리티가 로그인 요청을 가로채서 로그인 진행후 완료되면 
//고유 세선저장소에 userdetails 타입으로 저장됨
public class principaldetail implements UserDetails{
    
    private uservo uservo;//콤포지션

   public principaldetail(uservo uservo)
   {
       this.uservo=uservo;
   }

    @Override
    public String getPassword() {
        // TODO Auto-generated method stub
        return uservo.getpwd();
    }

    @Override
    public String getUsername() {
        // TODO Auto-generated method stub
        return uservo.getName();
    }
    ///꼐정이 만됴되지 않았는디 리턴한다(true만료안됨)
    @Override
    public boolean isAccountNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }
//꼐정이 잠겨있는지 리턴한다(true잠기지 않음)
    @Override
    public boolean isAccountNonLocked() {
        // TODO Auto-generated method stub
        return true;
    }
//비밀번호 만됴되지 않았는디 리턴한다(true만료안됨)
    @Override
    public boolean isCredentialsNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }
    //꼐정이 활성화 되어있는지 않았는디 리턴한다(true활성화)
    @Override
    public boolean isEnabled() {
        // TODO Auto-generated method stub
        return true;
    }
    //꼐정이 권한 리턴한다(true만료안됨)
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority>collection=new ArrayList<>();
        collection.add(new GrantedAuthority(){
            @Override
            public String getAuthority() {
                return "user";
            }
        });
        return null;
    }
}
