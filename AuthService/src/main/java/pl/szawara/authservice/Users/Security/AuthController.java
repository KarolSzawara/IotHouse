package pl.szawara.authservice.Users.Security;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.szawara.authservice.Repostitory.JpaUserSecurityService;
import pl.szawara.authservice.Users.Model.AuthenticationRequest;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class AuthController {
        private final AuthService service;
        @PostMapping("/authenticate")
        public ResponseEntity<String> authenticate(@RequestBody AuthenticationRequest request, HttpServletResponse response){
                return ResponseEntity.ok(service.authentication(request,response));
        }

}
