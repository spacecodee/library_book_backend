package com.spacecodee.library_book_backend.security;

import com.spacecodee.library_book_backend.jwt.JwtEntryPoint;
import com.spacecodee.library_book_backend.jwt.JwtTokenFilter;
import com.spacecodee.library_book_backend.service.user.client.PUserClientServiceImpl;
import com.spacecodee.library_book_backend.service.user.system.PUserSystemServiceImpl;
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

    private final PUserSystemServiceImpl userSystemService;
    private final PUserClientServiceImpl userClientService;
    private final JwtEntryPoint jwtEntryPoint;
    private final AuthenticationConfiguration authenticationConfiguration;

    public ApplicationSecurityConfig(PUserSystemServiceImpl userSystemService, PUserClientServiceImpl userClientService,
                                     JwtEntryPoint jwtEntryPoint,
                                     AuthenticationConfiguration authenticationConfiguration) {
        this.userSystemService = userSystemService;
        this.userClientService = userClientService;
        this.jwtEntryPoint = jwtEntryPoint;
        this.authenticationConfiguration = authenticationConfiguration;
    }

    @Bean
    public SecurityFilterChain configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.cors()
                    .and()
                    .csrf()
                    .disable()
                    .authorizeRequests()
                    .antMatchers(
                            "/v1/auth/**",
                            "/v1/user-system/**",
                            "/v1/user-client/**",
                            "/v1/api-docs/**",
                            "/swagger-ui/**",
                            "/swagger-resources/**",
                            "/configuration/**",
                            "/webjars/**"
                    ).permitAll()
                    .antMatchers(
                            HttpMethod.GET,
                            "/v1/book/**",
                            "/v1/category-book/**"
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
    public void configure(AuthenticationManagerBuilder builder) throws Exception {
        builder.userDetailsService(this.userSystemService).passwordEncoder(new BCryptPasswordEncoder());
        builder.userDetailsService(this.userClientService).passwordEncoder(new BCryptPasswordEncoder());
    }
}
