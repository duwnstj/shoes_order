package com.personal.common.config;

import com.personal.common.enums.UserRole;
import io.github.cdimascio.dotenv.Dotenv;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Date;

@RequiredArgsConstructor
@Component
public class JwtUtil {
    private final Dotenv dotenv;

    public static final String ACCESS = "ACCESS";
    public static final String REFRESH = "REFRESH";

    public static final String BEARER_PREFIX = "Bearer ";

    private SecretKey accessKey;

    private final long TOKEN_ACCESS_TIME = 60 * 60 * 24 * 1000L;

    private SecretKey refreshKey;

    private final long TOKEN_REFRESH_TIME = 60 * 60 * 24 * 1000L;


    // 로그 설정
    public static final Logger logger = LoggerFactory.getLogger("JWT 관련 로그");

    @PostConstruct
    public void init() {
        accessKey = Keys.hmacShaKeyFor(Base64.getDecoder().decode(dotenv.get("JWT_SECRET_ACCESS_TOKEN")));
        refreshKey = Keys.hmacShaKeyFor(Base64.getDecoder().decode(dotenv.get("JWT_SECRET_REFRESH_TOKEN")));
    }

    // 토큰 생성
    public String createToken(Long userId , String email , UserRole userRole , String type) {
        Date date = new Date();

        SecretKey secretKey = type.equals(ACCESS) ? accessKey : refreshKey;
        long time = type.equals(ACCESS) ? TOKEN_ACCESS_TIME : TOKEN_REFRESH_TIME;

        return BEARER_PREFIX +
                Jwts.builder()
                        .subject(String.valueOf(userId))
                        .claim("email" , email)
                        .claim("userRole", userRole)
                        .expiration(new Date(date.getTime() + time))
                        .issuedAt(date)
                        .signWith(secretKey)
                        .compact();
    }

    // 토큰 검증
    public boolean validateToken(String token , String type) {
        try {
            SecretKey secretKey = type.equals(ACCESS) ? accessKey : refreshKey;
            Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload();
            return true; // 유효한 토큰
        } catch (SecurityException | MalformedJwtException e) {
            logger.error("잘못된 JWT 서명입니다.");
        } catch (ExpiredJwtException e) {
            logger.error("만료된 JWT 토큰입니다.");
        } catch (UnsupportedJwtException e) {
            logger.error("지원되지 않는 JWT 토큰입니다.");
        } catch (IllegalArgumentException e) {
            logger.error("JWT 토큰이 잘못되었습니다.");
        }
        return false; // 유효하지 않은 토큰
    }

    // JWT 토큰 substring
    public String substringToken(String tokenValue) {
        if (StringUtils.hasText(tokenValue) && tokenValue.startsWith(BEARER_PREFIX)) {
            // "Bearer "이 공백을 포함하여 7자를 자른다.
            return tokenValue.substring(7);
        }
        logger.error("Not Found Token");
        throw new NullPointerException("Not Found Token");
    }

    // 토큰에서 사용자 정보 가져오기
    public Claims getUserInfoFromToken(String token , String type) {
        SecretKey secretKey = type.equals(ACCESS) ? accessKey : refreshKey;

        return Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
}
