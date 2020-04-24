package com.xhd.dynamic_privilege.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/product")
public class ProductController {

    @RequestMapping(value = "/findAll",method = RequestMethod.GET)
    public String findAll(){
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        System.out.println("权限信息");
        for (GrantedAuthority authority : authorities) {
            System.out.println(authority.getAuthority()+"--"+authority.toString());
        }
        System.out.println("------------------");
        Object principal = authentication.getPrincipal();
        System.out.println(principal.toString());
        UserDetails userDetails = (UserDetails)principal;
        System.out.println("用户信息");
        System.out.println("用户名："+userDetails.getUsername());
        System.out.println("密码："+userDetails.getPassword());
        for (GrantedAuthority grantedAuthority : userDetails.getAuthorities()) {
            System.out.println(grantedAuthority.toString());
        }
        return "product list data";
    }
}
