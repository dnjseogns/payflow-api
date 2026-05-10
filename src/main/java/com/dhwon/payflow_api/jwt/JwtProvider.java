package com.dhwon.payflow_api.jwt;

import com.dhwon.payflow_api.exception.CustomException;
import com.dhwon.payflow_api.response.enums.ResponseCode;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
@Getter
public class JwtProvider {

    private final Key key;
    private final long expiration;
    private final long refreshExpiration;

    public JwtProvider(
            @Value("${jwt.secret}") String secretKey,
            @Value("${jwt.expiration}") long expiration,
            @Value("${jwt.refresh-expiration}") long refreshExpiration
    ) {
        this.key = Keys.hmacShaKeyFor(secretKey.getBytes());
        this.expiration = expiration;
        this.refreshExpiration = refreshExpiration;
    }

    /**
     * JWT 생성
     */
    public String createToken(String userId, String roleCode){

        Date now = new Date();

        Date expireDate = new Date(now.getTime() + expiration);

        return Jwts.builder()
                .setSubject(userId)
                .claim("roleCode", roleCode)
                .setIssuedAt(now)
                .setExpiration(expireDate)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    /**
     * Refresh Token 생성
     */
    public String createRefreshToken(
            String userId
    ){

        Date now = new Date();
        Date expireDate = new Date(now.getTime() + refreshExpiration);

        return Jwts.builder()
                .setSubject(userId)
                .setIssuedAt(now)
                .setExpiration(expireDate)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    /**
     * userId 추출
     */
    public String getUserId(String token){
        return getClaims(token).getSubject();
    }

    /**
     * roleCode 추출
     */
    public String getRoleCode(String token){

        return getClaims(token)
                .get("roleCode", String.class);
    }

    /**
     * 만료일 추출
     */
    public Date getExpireDate(String token){

        return getClaims(token)
                .getExpiration();
    }


    /**
     * 토큰 검증
     */
    public void validateToken(String token){
        try {
            getClaims(token);
        } catch (ExpiredJwtException e){
            throw new CustomException(ResponseCode.EXPIRED_TOKEN);
        } catch (Exception e){
            throw new CustomException(ResponseCode.UNAUTHORIZED);
        }
    }

    /**
     * jwt에서 Claim 추출
     */

    public Claims getClaims(String token){

        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

}