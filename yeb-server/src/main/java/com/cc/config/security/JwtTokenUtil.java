package com.cc.config.security;


import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class JwtTokenUtil {
    public static final String CLAIM_KEY_USERNAME="sub";
    public static final String CLAIM_KEY_CREAT="created";
    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.expiration}")
    public Long expiration;


    //根据用户信息生成token
    public String generateToken(UserDetails userDetails){
        Map<String,Object> claims = new HashMap<>();
        claims.put(CLAIM_KEY_USERNAME,userDetails.getUsername());
        claims.put(CLAIM_KEY_CREAT,new Date());
        return generateToken(claims);

    }

    //从token中获取登录用户名
    public String getUserNameFromToken(String token){
        String username;
        try {
            Claims claims = getClaimsFromToken(token);
            username = claims.getSubject();
        } catch (Exception e) {
            username = null;
        }
        return username;
    }

    //验证token是否有效
    public boolean validateToken(String token,UserDetails userDetails){
        String username = getUserNameFromToken(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    //判断token是否可以被刷新
    public boolean canRefresh(String token){
        return !isTokenExpired(token);
    }

    //刷新token
    public String refreshToken(String token){
        Claims claims = getClaimsFromToken(token);
        claims.put(CLAIM_KEY_CREAT,new Date());
        return generateToken(claims);
    }

    //判断token是否失效
    private boolean isTokenExpired(String token) {
        Date expireDate = getExpiredDateFromToken(token);
        return expireDate.before(new Date());
    }

    //从token中获取过期时间
    private Date getExpiredDateFromToken(String token) {
        Claims claims = getClaimsFromToken(token);
        return claims.getExpiration();
    }



    //从token中获取荷载
    private Claims getClaimsFromToken(String token) {
        Claims claims = null;
        try {
            claims = Jwts.parser()
                   .setSigningKey(secret)
                   .parseClaimsJws(token)
                   .getBody();
        } catch (ExpiredJwtException e) {
            e.printStackTrace();
        }
        return claims;
    }

    //根据荷载生成JWT TOKEN
    private  String generateToken(Map<String,Object> claims){
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(generateExpirationDate())
                .signWith(SignatureAlgorithm.ES512,secret)
                .compact();
    }

    //生成token失效时间
    private Date generateExpirationDate() {
        return new Date(System.currentTimeMillis()+expiration*1000);
    }
}
