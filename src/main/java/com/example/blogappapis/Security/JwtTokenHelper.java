//package com.example.blogappapis.Security;
//
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.stereotype.Component;
//
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.function.Function;
//
//@Component
//public class JwtTokenHelper
//{
//    public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;
//
//    private String secret = "JwtTokenKey";
//
//    // Retrive the username from jwt token
//    public String getUsernameFromJwtToken(String token)
//    {
//        return getClaimFromToken(token, Claims::getSubject);
//    }
//
//    //Retrive the expiration date from jwt token
//    public Date getExpirationDateFromToken(String token)
//    {
//        return getClaimFromToken(token, Claims::getExpiration);
//    }
//    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver)
//    {
//        final Claims claims = getAllClaimsFromTokens(token);
//        return claimsResolver.apply(claims);
//    }
//    // for retriving any information from token we will need the secret key
//    private Claims getAllClaimsFromTokens(String token)
//    {
//        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
//    }
//
//    //check if the token has expired
//    private Boolean isTokenExpired(String token)
//    {
//        final Date expiration = getExpirationDateFromToken(token);
//        return expiration.before(new Date());
//    }
//
//    //generate token for user
//    public String generateToken(UserDetails userDetails)
//    {
//        Map<String, Object> claims = new HashMap<>();
//        return doGenerateToken(claims, userDetails.getUsername());
//    }
//
//    //while creating the token ->
//    //1.Define claims of the token like issuer, Expiration, Subject and ID
//    //2. Sign the Jwt using the HS512 algorithm and key
//    //3. According to jws compact serialization(https://tools.ietf.org/html/draft-left-jose)
//    //4.compaction of the JWT to a URL safe string
//    private String doGenerateToken(Map<String, Object> claims, String subject)
//    {
//        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
//                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
//                .signWith(SignatureAlgorithm.HS512, secret).compact();
//    }
//
//    //validate token
//    public Boolean validateToken(String token, UserDetails userDetails)
//    {
//        final String username = getUsernameFromJwtToken(token);
//        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
//    }
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//}
