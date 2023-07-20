package pl.szawara.authservice.Users.Security;

import org.springframework.security.core.userdetails.UserDetails;
import pl.szawara.authservice.Users.Model.Roles;
import pl.szawara.authservice.Users.Model.UserSecurity;

public interface JwtService {
    String extractUserName(String token);
    String generateToken(UserDetails userSecurity);
    boolean isTokenValid(String token);
    Roles getRole(String token);
}
