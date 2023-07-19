package pl.szawara.authservice.Users.Security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import pl.szawara.authservice.Users.Model.Roles;
import pl.szawara.authservice.Users.Model.UserSecurity;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
@Component
public class JwtServiceImpl implements JwtService{
    @Value(value = "${token.signing.key}")
    private String jwtSigningKey;
    @Override
    public String extractUserName(String token) {
        return extractClaim(token,Claims::getSubject);
    }

    @Override
    public String generateToken(UserDetails userSecurity) {
        var claims=new HashMap<String,Object>();
        claims.put("role", userSecurity.getAuthorities());
        return createToken(claims, userSecurity.getUsername());
    }


    @Override
    public boolean isTokenValid(String token) {
        return extractClaim(token,Claims::getExpiration).before(new Date());
    }

    @Override
    public Roles getRole(String token) {
        return extractAllClaims(token).get("role",Roles.class);
    }

    private Claims extractAllClaims(String token){
        return Jwts.parser().setSigningKey(getKey()).parseClaimsJws(token).getBody();
    }
    private <T> T extractClaim(String token, Function<Claims,T> claimsResolver){
        final var claims=extractAllClaims(token);
        return claimsResolver.apply(claims);
    }
    private String createToken(Map<String,Object> claims,String username){
        return Jwts.builder().setClaims(claims).setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+1000*60*24))
                .signWith(SignatureAlgorithm.HS384,getKey()).compact();
    }

    private String getKey() {
        return this.jwtSigningKey;
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(getKey());
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
