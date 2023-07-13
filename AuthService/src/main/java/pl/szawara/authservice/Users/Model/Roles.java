package pl.szawara.authservice.Users.Model;

import org.springframework.security.core.GrantedAuthority;

public enum Roles implements GrantedAuthority {
    Admin,
    User,
    Guest;

    @Override
    public String getAuthority() {
        return name();
    }
}
