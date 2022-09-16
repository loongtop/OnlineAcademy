package com.gkhy.serviceoauth2.token;

import com.gkhy.serviceoauth2.config.OAuth2Properties;
import com.gkhy.serviceoauth2.entity.User;
import com.gkhy.serviceoauth2.entity.UserPrincipal;
import com.gkhy.serviceoauth2.error.Oauth2Error;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.util.Date;

@Service
public class TokenProvider implements Serializable {

    private static final long serialVersionUID = 5777410839989209849L;
    private static final Logger logger = LoggerFactory.getLogger(TokenProvider.class);

    private OAuth2Properties oAuth2Properties;
    @Autowired
    public TokenProvider(OAuth2Properties oAuth2Properties) {
        this.oAuth2Properties = oAuth2Properties;
    }

    /**
     * create Token
     **/
    public String createToken(Authentication authentication) {
        User user = new User();
        BeanUtils.copyProperties(authentication.getPrincipal(), user);
        UserPrincipal userPrincipal = UserPrincipal.of(user);

        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + oAuth2Properties.getTokenParam().getTokenExpirationMsec());

        return Jwts.builder()
                .setHeaderParam("type", "JWT")
                .setHeaderParam("alg", "HS256")

                .setSubject("OnlineAcademy-user")
                .setIssuedAt(new Date())
                .setExpiration(expiryDate)

                .claim("id", userPrincipal.getId())  // Set the token body part to store user information
                .claim("name", userPrincipal.getName())

                .signWith(SignatureAlgorithm.HS512, oAuth2Properties.getTokenParam().getTokenSecret())
                .compact();
    }

    /**
     * Get member id based on token string
     * @param token : token
     * @return id : id
     */
    public String getUserIdFromToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(oAuth2Properties.getTokenParam().getTokenSecret())
                .parseClaimsJws(token)
                .getBody();

        return (String) claims.get("id");
    }


    /**
     * Determine whether the token exists and is valid
     **/
    public boolean validateToken(String authToken) {
        if(!StringUtils.hasLength(authToken)) return false;

        try {
            Jwts.parser().setSigningKey(oAuth2Properties.getTokenParam().getTokenSecret()).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException ex) {
            logger.error(Oauth2Error.EXPIRED_JWT_TOKEN.getMessage());
        } catch (MalformedJwtException ex) {
            logger.error(Oauth2Error.INVALID_JWT_TOKEN.getMessage());
        } catch (ExpiredJwtException ex) {
            logger.error(Oauth2Error.EXPIRED_JWT_TOKEN.getMessage());
        } catch (UnsupportedJwtException ex) {
            logger.error(Oauth2Error.UNSUPPORTED_JWT_TOKEN.getMessage());
        } catch (IllegalArgumentException ex) {
            logger.error(Oauth2Error.EMPTY_JWT_TOKEN.getMessage());
        }
        return false;
    }
}
