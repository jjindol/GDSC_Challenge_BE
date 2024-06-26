package com.streetreview.member.security;

import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.ServletRequest;
import java.util.Date;

@RequiredArgsConstructor
@Component
public class JwtValidator {
    @Value("${jwt.access.key}")
    private String accessKey;

    //토큰의 유효성 검사
    public boolean validateToken(ServletRequest request, String jwtToken) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(accessKey.getBytes()).parseClaimsJws(jwtToken);
            if(claims.getBody().getExpiration() == null) return true;
            return !claims.getBody().getExpiration().before(new Date());
        } catch (SignatureException e) {
            request.setAttribute("exception", "ForbiddenException");
        } catch (MalformedJwtException e) {
            request.setAttribute("exception", "MalformedJwtException");
        } catch (ExpiredJwtException e) {
            request.setAttribute("exception", "ExpiredJwtException");
        } catch (UnsupportedJwtException e) {
            request.setAttribute("exception", "UnsupportedJwtException");
        } catch (IllegalArgumentException e) {
            request.setAttribute("exception", "IllegalArgumentException");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // RefreshToken 유효성 검증 메소드
//    public String validateRefreshToken(RefreshToken refreshTokenObj) {
//        String refreshToken = refreshTokenObj.getRefreshToken();
//
//        try {
//            Jws<Claims> claims = Jwts.parser().setSigningKey(refreshSecretKey).parseClaimsJws(refreshToken);
//            //AccessToken이 만료되지않았을떄만
//            /*if(!claims.getBody().getExpiration().before(new Date())) {
//                return recreationAccessToken(claims.getBody().get("sub").toString(), claims.getBody().get("roles"));
//            }*/
//            return recreationAccessToken(claims.getBody().get("sub").toString(), claims.getBody().get("roles"));
//        }catch (Exception e) {
//            e.printStackTrace();
//            throw new CustomException(StatusCode.EXPIRED_JWT);
//        }
//    }
//
//    //AccessToken 새로 발급
//    private String recreationAccessToken(String email, Object roles) {
//        Claims claims = Jwts.claims().setSubject(email);
//        claims.put("roles", roles);
//        Date currentTime = new Date();
//        return getToken(claims, currentTime, accessTokenValidTime, accessSecretKey);
//    }
}
