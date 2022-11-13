package com.spacecodee.library_book_backend.jwt;

import com.spacecodee.library_book_backend.service.user.client.UserClientServiceImpl;
import com.spacecodee.library_book_backend.service.user.system.UserSystemServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtTokenFilter extends OncePerRequestFilter {

    private static final Logger LOGGER = LoggerFactory.getLogger(JwtTokenFilter.class);

    @Autowired
    private JwtProvider jwtProvider;
    @Autowired
    private UserSystemServiceImpl userDetailsService;
    @Autowired
    private UserClientServiceImpl userClientService;

    @Override
    @NonNull
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain)
            throws ServletException, IOException {
        UserDetails userDetails;

        try {
            String token = getToken(request);

            if (token != null && this.jwtProvider.validateToken(token)) {
                String username = this.jwtProvider.getUsernameFromToken(token);
                userDetails = getUserSystemOrClient(username);

                UsernamePasswordAuthenticationToken authenticationToken =
                        new UsernamePasswordAuthenticationToken(
                                userDetails, null,
                                userDetails.getAuthorities()
                        );

                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
            JwtTokenFilter.LOGGER.error("fail en el método doFilter: {}", e.getMessage());
        }

        filterChain.doFilter(request, response);
    }

    private UserDetails getUserSystemOrClient(String username) {
        UserDetails userDetails;

        userDetails = this.userDetailsService.loadUserByUsername(username);
        if (userDetails.getAuthorities() == null) {
            userDetails = this.userClientService.loadUserByUsername(username);
        }

        return userDetails;
    }

    private String getToken(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        if (header != null && header.startsWith("Bearer")) {return header.replace("Bearer ", "");}
        return null;
    }
}
