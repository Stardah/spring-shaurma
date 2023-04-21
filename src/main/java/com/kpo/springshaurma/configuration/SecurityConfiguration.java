package com.kpo.springshaurma.configuration;

import com.kpo.springshaurma.jwt.JwtConfigurer;
import com.kpo.springshaurma.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Security configuration class for JWT based Spring Security application.
 */
@Configuration
@RequiredArgsConstructor
public class SecurityConfiguration {

    private static final String[] PERMIT_ALL = {
            "/v3/api-docs/**",
            "/swagger-ui/**",
            "/api/v2/api-docs/**",
            "/system/**",
            "/uploadFile/**",
            "/h2-console/**"
    };

    private static final String LOGIN_ENDPOINT = "/api/v1.0/login";
    private static final String REFRESH_ENDPOINT = "/api/v1.0/refresh";
    private static final String REGISTER_ENDPOINT = "/api/v1.0/register";

    private final JwtTokenProvider jwtTokenProvider;

    @Bean
    @SneakyThrows
    public SecurityFilterChain filterChain(HttpSecurity http) {
        return http.httpBasic().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .requestMatchers(
                        LOGIN_ENDPOINT,
                        REFRESH_ENDPOINT,
                        REGISTER_ENDPOINT
                )
                .permitAll()
                .requestMatchers(
                        PERMIT_ALL
                )
                .permitAll()
                .anyRequest().authenticated()
                .and().build();
    }
}
