package com.interswitch.codingassessment.security.service;

import com.interswitch.codingassessment.security.models.LoginUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @Author bkaaron
 * @Project coding-assessment
 * @Date 7/29/23
 **/
@Service
public class JwtService {
    static final String CLAIM_SCOPES="scopes";
    static final String CLAIM_USER_ID="userid";

    @Value("${token.signing.key}")
    private String secretKey;
    private JwtService() { }

    public String extractUsername(String token){
        return extractClaim(token, Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver){
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public String generateToken(UserDetails userDetails){
        Map<String, Object> claims = new HashMap<>();

        claims.put(CLAIM_SCOPES, userDetails.getAuthorities().stream()
                .map(Object::toString).collect(Collectors.toList()));

        claims.put(CLAIM_USER_ID, ((LoginUser)userDetails).getId());

        return generateToken(claims, userDetails);
    }

    public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails){

        final Date createdDate = new Date(System.currentTimeMillis());
        final Date expirationDate = calculateExpirationDate(createdDate);

        return Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(createdDate)
                .setExpiration(expirationDate)
                .signWith(getSignInKey(), SignatureAlgorithm.HS512)
                .compact();
    }

    public String refreshToken(String token) {
        final Date createdDate = new Date(System.currentTimeMillis());
        final Date expirationDate = calculateExpirationDate(createdDate);

        final Claims claims = getAllClaimsFromToken(token);
        claims.setIssuedAt(createdDate);
        claims.setExpiration(expirationDate);


        return Jwts.builder()
                .setClaims(claims)
                .signWith(getSignInKey(), SignatureAlgorithm.HS512)
                .compact();
    }

    public boolean isTokenValid(String token, UserDetails userDetails){
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    public Boolean canTokenBeRefreshed(String token, Date lastPasswordReset) {
        final Date created = getIssuedAtDateFromToken(token);
        return ignoreTokenExpiration(token) || !isTokenExpired(token);
    }

    private Boolean ignoreTokenExpiration(String token) {
        // here you specify tokens, for that the expiration is ignored
        return Boolean.FALSE;

    }

    public Date getIssuedAtDateFromToken(String token) {
        return extractClaim(token, Claims::getIssuedAt);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token){
        return extractClaim(token, Claims::getExpiration);
    }

    private Claims extractAllClaims(String token){
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSignInKey(){
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    private Date calculateExpirationDate(Date createdDate) {
        return new Date(createdDate.getTime() + 1000 * 60 * 60 * 24);
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
