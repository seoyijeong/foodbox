package com.groupfour.foodbox.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.web.filter.CharacterEncodingFilter;


//@EnableWebSecurity
    @Configuration
    public class SecurityConfig extends WebSecurityConfigurerAdapter {
        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.csrf().disable();  // post 방식으로 값을 전송할 때 token을 사용해야하는 보안 설정을 해제
            http.formLogin().disable();
            System.out.println("SecurityConfig");
        }

        @Bean
        public PasswordEncoder passwordEncoder() {
            System.out.println("PasswordEncoder");
            return new BCryptPasswordEncoder();
        }
    }


