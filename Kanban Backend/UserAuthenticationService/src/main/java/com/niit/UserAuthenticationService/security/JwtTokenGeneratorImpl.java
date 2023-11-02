package com.niit.UserAuthenticationService.security;

import com.niit.UserAuthenticationService.domain.User;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Date;

@Service
public class JwtTokenGeneratorImpl implements SecurityTokenGenerator{
    public String createToken(User user){
        Map<String,Object> claims=new HashMap<>();
        claims.put("user Id",user.getEmailId());

        return generateToken(claims, user.getEmailId());

    }

    public String generateToken(Map<String,Object> claims,String subject) {
        // Generate the token and set the user id in the claims
        String jwtToken =Jwts.builder().setIssuer("Customer")
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256,"secret")
                .compact();
        return jwtToken;

    }
}
