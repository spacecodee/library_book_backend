package com.spacecodee.library_book_backend.security;

import com.spacecodee.library_book_backend.jwt.JwtEntryPoint;
import com.spacecodee.library_book_backend.jwt.JwtTokenFilter;
import com.spacecodee.library_book_backend.service.user.client.PrincipalClientServiceImpl;
import com.spacecodee.library_book_backend.service.user.system.PrincipalUserServiceImpl;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ApplicationSecurityConfig {

    private final PrincipalUserServiceImpl userSystemService;
    private final PrincipalClientServiceImpl userClientService;
    private final JwtEntryPoint jwtEntryPoint;
    private final AuthenticationConfiguration authenticationConfiguration;

    public ApplicationSecurityConfig(PrincipalUserServiceImpl userSystemService,
                                     PrincipalClientServiceImpl userClientService,
                                     JwtEntryPoint jwtEntryPoint,
                                     AuthenticationConfiguration authenticationConfiguration) {
        this.userSystemService = userSystemService;
        this.userClientService = userClientService;
        this.jwtEntryPoint = jwtEntryPoint;
        this.authenticationConfiguration = authenticationConfiguration;
    }

    @Bean
    public SecurityFilterChain configure(@NotNull HttpSecurity httpSecurity) throws Exception {
        httpSecurity.cors()
                    .and()
                    .csrf()
                    .disable()
                    .authorizeRequests()
                    .antMatchers(
                            "/v1/auth/**",
                            "/v2/api-docs/**",
                            "/swagger-ui/**",
                            "/swagger-resources/**",
                            "/configuration/**",
                            "/webjars/**"
                    ).permitAll()
                    .antMatchers(
                            HttpMethod.GET,
                            "/v1/book/get-all",
                            "/v1/book/get-by/{id}",
                            "/v1/book/find-by/{bookId}",
                            "/v1/category-book/get-all",
                            "/v1/category-book/get-by/{id}",
                            "/v1/category-book/find-all",
                            "/v1/category-book/find-by/{id}",
                            "/v1/rating-book/list"
                    ).permitAll()
                    .anyRequest()
                    .authenticated()
                    .and()
                    .exceptionHandling()
                    .authenticationEntryPoint(this.jwtEntryPoint)
                    .and()
                    .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        httpSecurity.addFilterBefore(this.jwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
        return httpSecurity.build();
    }

    @Bean
    public JwtTokenFilter jwtTokenFilter() {
        return new JwtTokenFilter();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    AuthenticationManager authenticationManager() throws Exception {
        return this.authenticationConfiguration.getAuthenticationManager();
    }

    @Autowired
    public void configure(@NotNull AuthenticationManagerBuilder builder) throws Exception {
        builder.userDetailsService(this.userClientService).passwordEncoder(new BCryptPasswordEncoder());
        builder.userDetailsService(this.userSystemService).passwordEncoder(new BCryptPasswordEncoder());
    }
}
