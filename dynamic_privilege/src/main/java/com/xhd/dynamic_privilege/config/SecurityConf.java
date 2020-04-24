package com.xhd.dynamic_privilege.config;

import com.xhd.dynamic_privilege.service.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConf extends WebSecurityConfigurerAdapter {

    @Bean
    public UserDetailsService userDetailsService() {
        return new CustomUserDetailsService();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("admin").password("{noop}123").roles("ADMIN","USER").and()
                .withUser("user").password("{noop}123").roles("USER");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/login.jsp", "failer.jsp", "/css/**", "/img/**", "/plugins/**").permitAll()
                .antMatchers("/admin","admin").hasAnyRole("ADMIN")
                .antMatchers("/**").hasAnyRole("USER").anyRequest().authenticated()
                .and()
                .formLogin().loginPage("/login.jsp").loginProcessingUrl("/login").defaultSuccessUrl("/index.jsp").failureForwardUrl("/failer.jsp").permitAll()
                .and()
                .logout().logoutUrl("/logout").invalidateHttpSession(true).logoutSuccessUrl("/login.jsp").permitAll()
                .and()
                .csrf().disable();
    }
}
