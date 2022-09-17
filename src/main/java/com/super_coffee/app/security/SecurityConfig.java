package com.super_coffee.app.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableGlobalMethodSecurity( prePostEnabled = true, securedEnabled = true, jsr250Enabled = true )
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain( HttpSecurity http ) throws Exception {
        http.csrf().disable().sessionManagement().sessionCreationPolicy( STATELESS )
                .and()
                .authorizeRequests().antMatchers( "/**" ).permitAll();

        return http.build();
    }
}