package pl.szawara.authservice.Users.Security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.test.util.ReflectionTestUtils;
import pl.szawara.authservice.Users.Model.Roles;
import pl.szawara.authservice.Users.Model.UserSecurity;
import pl.szawara.authservice.Users.Model.UserStatus;
import pl.szawara.authservice.Users.Model.Users;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class JwtServiceImplTest {
    @Mock
    UserSecurity userSecurity;
    @InjectMocks
    JwtServiceImpl jwtService;
    @Test
    void extractUserName() {

    }

    @Test
    void generateToken() {
        ReflectionTestUtils.setField(jwtService, "jwtSigningKey", "newKey");
        var username = "testUser";
        var user=new UserSecurity(new Users(1l,"a@a.pl",Roles.User,"Random pass", UserStatus.Active));
        var token=jwtService.generateToken(user);

        // Act
        var result = Jwts.parser().setSigningKey("newKey").parseClaimsJws(token).getBody();

        // Assert
        assertEquals(result.getSubject(),user.getUsername());
    }

    @Test
    void isTokenValid() {
    }

    @Test
    void getRole() {
    }
}