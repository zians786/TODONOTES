package com.bridgeit.utility;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.bridgeit.dao.JwtUserDto;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;

/**
 * Class validates a given token by using the secret configured in the application
 *
 * @author pascal alma
 */
@Component
public class JwtTokenValidator {



    @Value("${jwt.secret}")
    private String secret;

    /**
     * Tries to parse specified String as a JWT token. If successful, returns User object with username, id and role prefilled (extracted from token).
     * If unsuccessful (token is invalid or not containing all required user properties), simply returns null.
     *
     * @param token the JWT token to parse
     * @return the User object extracted from specified token or null if a token is invalid.
     */
    public JwtUserDto parseToken(String token) {
        JwtUserDto u = null;

        try {
            Claims body = Jwts.parser()
                    .setSigningKey("qazxswed")
                    .parseClaimsJws(token)
                    .getBody();

            u = new JwtUserDto();
            u.setUsername(body.getSubject());
            u.setId(Long.parseLong((String) body.get("userId")));
            u.setRole((String) body.get("role"));

        } catch (JwtException e) {
            // Simply print the exception and null will be returned for the userDto
            e.printStackTrace();
        }
        return u;
    }
}
