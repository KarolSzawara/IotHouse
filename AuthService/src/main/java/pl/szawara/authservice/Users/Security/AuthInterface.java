package pl.szawara.authservice.Users.Security;

import jakarta.servlet.http.HttpServletResponse;
import pl.szawara.authservice.Users.Model.AuthenticationRequest;

public interface AuthInterface {
    String authentication(AuthenticationRequest authRequest, HttpServletResponse response);
}
