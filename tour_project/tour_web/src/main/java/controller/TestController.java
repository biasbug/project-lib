package controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collection;

@Controller
@RequestMapping("/test")
public class TestController {

    @RequestMapping("test")
    public String test(){
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        User principal = (User)authentication.getPrincipal();
        Collection<GrantedAuthority> authorities = principal.getAuthorities();
        for (GrantedAuthority authority : authorities) {
            System.out.println(authority.toString());
            System.out.println(authority.getAuthority());
        }
        System.out.println("--------------------s");
        System.out.println(principal.getUsername());
        System.out.println(principal.getPassword());
        return "test";
    }
}
