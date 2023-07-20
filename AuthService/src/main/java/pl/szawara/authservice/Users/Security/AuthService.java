package pl.szawara.authservice.Users.Security;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import pl.szawara.authservice.Repostitory.JpaUserSecurityService;
import pl.szawara.authservice.Repostitory.UsersRepository;
import pl.szawara.authservice.Users.Model.AuthenticationRequest;

import java.util.ArrayList;

@Service
public class AuthService implements AuthInterface {
    private final JwtServiceImpl jwtService;
    private final UsersRepository usersRepository;
    private final AuthenticationManager manager;
    private final JpaUserSecurityService securityService;
    @Autowired
    public AuthService(JwtServiceImpl jwtService, UsersRepository usersRepository, AuthenticationManager manager, JpaUserSecurityService securityService) {
        this.jwtService = jwtService;
        this.usersRepository = usersRepository;
        this.manager = manager;
        this.securityService = securityService;
    }


    @Override
    public String authentication(AuthenticationRequest authRequest, HttpServletResponse response) {
        manager
                .authenticate(new UsernamePasswordAuthenticationToken(authRequest.getLogin(),authRequest.getPassword(),new ArrayList<>()));
       var user =securityService.loadUserByUsername(authRequest.getLogin());

       var jwt=jwtService.generateToken(user);

       response.addCookie(createCookie(jwt));

       return jwt;
    }
    private Cookie createCookie(String token){
        var cookie=new Cookie("jwt",token);
        cookie.setMaxAge(7*24*60*60);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        return cookie;
    }
}
