package com.example.umc10th.global.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    // 1. 방금 만든 에러 처리 클래스들 주입받기
    private final CustomAuthenticationEntryPoint customAuthenticationEntryPoint;
    private final CustomAccessDeniedHandler customAccessDeniedHandler;

    // 2. 로그인 없이 접근 가능한 Public API 목록 작성
    private final String[] allowUris = {
            "/swagger-ui/**",
            "/swagger-resources/**",
            "/v3/api-docs/**",
            "/api/v1/auth/signup" // 🔥 미션 요구사항: 회원가입 API는 Public
    };

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)

                // 3. Public / Private API 접근 권한 설정
                .authorizeHttpRequests(requests -> requests
                        .requestMatchers(allowUris).permitAll() // allowUris에 있는 건 모두 허용 (Public)
                        .anyRequest().authenticated()           // 그 외 나머지 API는 전부 인증(로그인) 필요 (Private)
                )

                // 4. 에러 발생 시 방금 만든 클래스들이 응답하도록 설정
                .exceptionHandling(exceptionHandling -> exceptionHandling
                        .authenticationEntryPoint(customAuthenticationEntryPoint) // 401 (인증 실패)
                        .accessDeniedHandler(customAccessDeniedHandler)         // 403 (인가 실패)
                )

                // 기타 기본 설정 (폼 로그인, 로그아웃)
                .formLogin(form -> form
                        .defaultSuccessUrl("/swagger-ui/index.html", true)
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout")
                        .permitAll()
                );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
