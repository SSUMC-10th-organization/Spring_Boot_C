package com.example.umc10th.global.config;

import com.example.umc10th.global.security.CustomAccessDenied;
import com.example.umc10th.global.security.CustomEntryPoint;
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
public class SecurityConfig {

    private final String[] allowUris = {
            // Swagger 허용
            "/swagger-ui/**",
            "/swagger-resources/**",
            "/v3/api-docs/**",

            // 로그인
            "/auth/**"
    };

    // 필터체인 메소드
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(requests -> requests
                        .requestMatchers(allowUris).permitAll() // 명시적으로 허용된 Uri들 빼고,
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .defaultSuccessUrl("/swagger-ui/index.html", true) // 로그인 성공시 스웨거페이지로
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout") // 로그아웃 성공시 해당 주소로 리다이렉트
                        .permitAll()
                )
                // 예외사항 핸들러
                .exceptionHandling(exception -> exception
                        .accessDeniedHandler(customAccessDenied())
                        .authenticationEntryPoint(customEntryPoint())
                );



        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // 인가실패
    @Bean
    public CustomAccessDenied customAccessDenied(){
        return new CustomAccessDenied();
    }

    // 인증실패
    @Bean
    public CustomEntryPoint customEntryPoint(){
        return new CustomEntryPoint();
    }
}