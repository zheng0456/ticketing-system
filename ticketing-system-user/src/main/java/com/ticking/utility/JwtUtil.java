package com.ticking.utility;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.util.Date;
import java.nio.charset.StandardCharsets;

public class JwtUtil {
    // 使用固定的密钥，确保应用重启后token仍然有效
    // 密钥长度必须至少为512位(64字节)以支持HS512算法
    private static final String SECRET_KEY = "ticketingSystemSecretKeyForJwtToken20250101SecureKeyForHS512AlgorithmWithSufficientLength!@#$%^&*()_+1234567890";
    private static final SecretKey SECRET = Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));
    // 过期时间，单位毫秒，默认24小时
    private static final long EXPIRATION_TIME = 24 * 60 * 60 * 1000;

    /**
     * 生成JWT Token
     * @param userId 用户ID
     * @param username 用户名
     * @return JWT Token
     */
    public static String generateToken(Long userId, String username) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + EXPIRATION_TIME);

        return Jwts.builder()
                .setSubject(username)
                .claim("userId", userId)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SECRET, SignatureAlgorithm.HS512)
                .compact();
    }

    /**
     * 解析JWT Token
     * @param token JWT Token
     * @return Claims
     */
    public static Claims parseToken(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(SECRET)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 验证JWT Token是否有效
     * @param token JWT Token
     * @return true表示有效，false表示无效
     */
    public static boolean validateToken(String token) {
        try {
            parseToken(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 从Token中获取用户ID
     * @param token JWT Token
     * @return 用户ID
     */
    public static Long getUserIdFromToken(String token) {
        Claims claims = parseToken(token);
        if (claims != null) {
            return claims.get("userId", Long.class);
        }
        return null;
    }

    /**
     * 从Token中获取用户名
     * @param token JWT Token
     * @return 用户名
     */
    public static String getUsernameFromToken(String token) {
        Claims claims = parseToken(token);
        if (claims != null) {
            return claims.getSubject();
        }
        return null;
    }
}