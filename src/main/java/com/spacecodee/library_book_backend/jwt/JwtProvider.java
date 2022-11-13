package com.spacecodee.library_book_backend.jwt;

import com.nimbusds.jwt.JWT;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.JWTParser;
import com.spacecodee.library_book_backend.mappers.user.client.IUserClientMapper;
import com.spacecodee.library_book_backend.mappers.user.system.IUserSystemReadMapper;
import com.spacecodee.library_book_backend.model.dto.jwt.JwtDto;
import com.spacecodee.library_book_backend.model.dto.user.client.PUserClientDto;
import com.spacecodee.library_book_backend.model.dto.user.system.PUserSystemDto;
import io.jsonwebtoken.*;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

@Component
public class JwtProvider {

    private static final Logger LOGGER = LoggerFactory.getLogger(JwtProvider.class);
    private static final String ROLES_CLAIM = "roles";
    private static final String ERROR = "Error: {}";

    @Value("${jwt.secret}")
    private String secretKey;
    @Value("${jwt.expiration}")
    private int expiration;

    public String generateToken(@NotNull Authentication authentication) {
        String username;
        List<String> roles;
        PUserClientDto principalUser;
        PUserSystemDto principalSystem;

        try {
            principalUser = (PUserClientDto) authentication.getPrincipal();

            username = principalUser.getUsername();
            roles = IUserClientMapper.INSTANCE.getUserClientRoles(principalUser);
        } catch (ClassCastException e) {
            principalSystem = (PUserSystemDto) authentication.getPrincipal();

            username = principalSystem.getUsername();
            roles = IUserSystemReadMapper.INSTANCE.getUserSystemRoles(principalSystem);
        }

        return this.getTokenForAll(username, roles);
    }

    public String getUsernameFromToken(String token) {
        return Jwts.parser()
                   .setSigningKey(this.secretKey.getBytes())
                   .parseClaimsJws(token)
                   .getBody()
                   .getSubject(); // no
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser()
                .setSigningKey(this.secretKey.getBytes())
                .parseClaimsJws(token);
            return true;
        } catch (MalformedJwtException ex) {
            JwtProvider.LOGGER.error(JwtProvider.ERROR, ex.getMessage());
            JwtProvider.LOGGER.error("Token mal formado");
        } catch (UnsupportedJwtException ex) {
            JwtProvider.LOGGER.error(JwtProvider.ERROR, ex.getMessage());
            JwtProvider.LOGGER.error("token no soportado");
        } catch (ExpiredJwtException ex) {
            JwtProvider.LOGGER.error(JwtProvider.ERROR, ex.getMessage());
            JwtProvider.LOGGER.error("token expirado");
        } catch (IllegalArgumentException ex) {
            JwtProvider.LOGGER.error(JwtProvider.ERROR, ex.getMessage());
            JwtProvider.LOGGER.error("token vacío");
        } catch (SignatureException ex) {
            JwtProvider.LOGGER.error(JwtProvider.ERROR, ex.getMessage());
            JwtProvider.LOGGER.error("fail en la firma");
        }

        return false;
    }


    public String refreshToken(@NotNull JwtDto dto) throws ParseException {
        JWT jwt = JWTParser.parse(dto.getToken());
        JWTClaimsSet claimsSet = jwt.getJWTClaimsSet();
        String username = claimsSet.getSubject();

        @SuppressWarnings("unchecked")
        List<String> roles = (List<String>) claimsSet.getClaim(JwtProvider.ROLES_CLAIM);
        return this.getTokenForAll(username, roles);
    }

    private String getTokenForAll(String username, List<String> roles) {
        return Jwts.builder()
                   .setSubject(username)
                   .claim(JwtProvider.ROLES_CLAIM, roles)
                   .setIssuedAt(new Date())
                   .setExpiration(new Date(new Date().getTime() + this.expiration * 180L))
                   .signWith(SignatureAlgorithm.HS512, this.secretKey.getBytes())
                   .compact();
    }
}
