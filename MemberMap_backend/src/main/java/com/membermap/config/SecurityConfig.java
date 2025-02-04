package com.membermap.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())  // CSRF 보호 비활성화
            .cors(cors -> cors.disable())  // CORS 보호 비활성화
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/**").permitAll() // 모든 API 허용
                .anyRequest().authenticated()
            )
            .formLogin(login -> login.disable()) // 로그인 폼 비활성화
            .httpBasic(basic -> basic.disable()); // 기본 인증 비활성화
        return http.build();
    }
}
