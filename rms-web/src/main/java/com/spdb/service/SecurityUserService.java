package com.spdb.service;

import com.mysql.cj.util.StringUtils;
import com.spdb.pojo.User;
import com.spdb.utils.EncryptUtils;
import com.spdb.utils.UserThreadLocal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SecurityUserService implements UserDetailsService {

    @Autowired
    UserService userService;

    @Autowired
    IdentService identService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        GrantedAuthority DEFAULT_ROLE = new SimpleGrantedAuthority("USER");
        GrantedAuthority ROOT_ROLE = new SimpleGrantedAuthority("ROOT");
        GrantedAuthority ADMIN_ROLE = new SimpleGrantedAuthority("ADMIN");


        // 1. 获取用户密码
        User user = userService.getUserByUsername(username);
//        System.out.println(user);
        if (user == null){
            return null;
        }
        // 2. 放入ThreadLocal中
        UserThreadLocal.put(user);
        // 3. 设置角色
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        String dbRole = identService.getIdentityInfo(user.getIdentity());
//        System.out.println(dbRole);
        if(StringUtils.isNullOrEmpty(dbRole)){
            grantedAuthorities.add(DEFAULT_ROLE);
        }else{
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(dbRole);
            grantedAuthorities.add(grantedAuthority);
        }

        UserDetails userDetails = new org.springframework.security.core.userdetails.User(username, user.getPassword(), grantedAuthorities);
        return userDetails;
    }
}
