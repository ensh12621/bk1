package com.kkh.backend.security;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.kkh.backend.filter.JwtAuthenticationFilter;
import com.kkh.backend.service.AuthService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	private AuthService authService;
	

	public SecurityConfig(AuthService authService) {
		this.authService = authService;
	}
	
	
	
	
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            
            .cors(cors -> cors.configurationSource(corsConfigurationSource()))

            // REST API 서버 환경일 경우 세션을 사용하지 않으므로 STATELESS로 설정 (JWT 사용 시 필수)
            .sessionManagement(session -> session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            )

            // URL별 접근 권한 관리 설정
            .authorizeHttpRequests(auth -> 
            	auth.requestMatchers("/student/**").authenticated()
            	.anyRequest().permitAll()
        	)
            
            .formLogin(form -> form.disable())
            .addFilterBefore( new JwtAuthenticationFilter(authService), UsernamePasswordAuthenticationFilter.class);
                
                

        // 기본 폼 로그인이나 HTTP Basic 인증을 사용하지 않고 직접 커스텀할 때 설정
        // .formLogin(form -> form.disable());

        return http.build();
    }
	
    
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        
        // 허용할 프론트엔드 출처(Origin) 지정
        configuration.setAllowedOrigins(List.of("http://localhost:3000"));
        
        // 허용할 HTTP 메서드 지정
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        
        // 허용할 헤더 지정
        configuration.setAllowedHeaders(List.of("*"));
        
        // 자격 증명(쿠키, Authorization 헤더 등)을 허용할지 여부 (로그인 연동 시 필수)
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        // 모든 경로("/**")에 대해 위 정책을 적용
        source.registerCorsConfiguration("/**", configuration);
        
        return source;
    }
}
