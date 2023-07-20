package pl.szawara.authservice.Users.Security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.junit.jupiter.api.BeforeEach;
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

    @InjectMocks
    JwtServiceImpl jwtService;
    String token;
    UserSecurity userSecurity;

    @BeforeEach
    void init(){
        ReflectionTestUtils.setField(jwtService, "jwtSigningKey", "newKey");
        userSecurity=new UserSecurity(new Users(1l,"a@a.pl",Roles.User,"Random pass", UserStatus.Active));
        token=jwtService.generateToken(userSecurity);
    }
    @Test
    void extractUserName() {



        var result = Jwts.parser().setSigningKey("newKey").parseClaimsJws(token).getBody();

        assertEquals(result.getSubject(),userSecurity.getUsername());
    }



    @Test
    void isTokenValid() {
        assertEquals(false,jwtService.isTokenValid(token));
    }


}