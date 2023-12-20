package com.example.demo;

import com.example.demo.users.CustomUserDetailService;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

/*
spring security custom configuration
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth ->{
                    auth
                        .requestMatchers("/emp/**").permitAll()
                        .requestMatchers("/reserve/**").permitAll()
                        .requestMatchers("/hello/**").hasRole("USER")
//                        .requestMatchers("/**").hasRole("USER")
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .anyRequest().authenticated();
                }).httpBasic(Customizer.withDefaults())
                .exceptionHandling(Customizer.withDefaults());
                //.requestMatchers("/**").hasRole("USER")
                //.and()
                //.formLogin();
        return http.build();
    }



    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new CustomUserDetailService();
//        UserDetails user =
//                User.builder()
//                        .username("user")
//                        .password(passwordEncoder().encode("1234"))
//                        .roles("USER")
//                        .build();
//        return new InMemoryUserDetailsManager(user);
    }

    //SecurityAutoConfiguration
}
