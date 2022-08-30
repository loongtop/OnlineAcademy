package com.gkhy.commonutils.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.experimental.UtilityClass;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @author leo
 * @since 2022/07/21
 */
@UtilityClass
public class JwtUtils {

    public static final long EXPIRE = 1000 * 60 * 60 * 24; //expiration time
    public static final String APP_SECRET = "5ac84DbRigUDaY6pZFfWus2jZWLPHO"; //秘钥

    //to generate token string
    public static String getJwtToken(String id, String nickname){

        return Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setHeaderParam("alg", "HS256")

                .setSubject("guli-user")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE))

                .claim("id", id)  // Set the token body part to store user information
                .claim("nickname", nickname)

                .signWith(SignatureAlgorithm.HS256, APP_SECRET)
                .compact();
    }

    /**
     * Determine whether the token exists and is valid
     */
    public static boolean checkToken(String jwtToken) {
        if(!StringUtils.hasLength(jwtToken)) return false;
        try {
            Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(jwtToken);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * Determine whether the token exists and is valid
     */
    public static boolean checkToken(HttpServletRequest request) {
        try {
            String jwtToken = request.getHeader("token");
            if(!StringUtils.hasLength(jwtToken)) return false;
            Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(jwtToken);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * Get member id based on token string
     * @param request : Http ServletRequest
     * @return id : id
     */
    public static String getMemberIdByJwtToken(HttpServletRequest request) {
        String jwtToken = request.getHeader("token");
        if(!StringUtils.hasLength(jwtToken)) return "";
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(jwtToken);
        Claims claims = claimsJws.getBody();
        return (String)claims.get("id");
    }
}
