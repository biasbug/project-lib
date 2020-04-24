package com.xhd.dynamic_privilege.service;

import com.xhd.dynamic_privilege.domain.User;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomUserDetailsService implements UserDetailsService {

    private static Map<String, User> userMap = new HashMap<String, User>();

    static {
        User user = new User("admin", "{noop}123456", "admin");
        userMap.put(user.getUsername(), user);
        user = new User("selfly", "{noop}123456", "user");
        userMap.put(user.getUsername(), user);

    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userMap.get(s);
        if (user == null) {
            throw new UsernameNotFoundException("not found");
        }
        List<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + user.getRole()));
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),authorities);
    }

}
