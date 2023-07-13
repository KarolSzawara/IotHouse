package pl.szawara.authservice.Repostitory;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import pl.szawara.authservice.Users.Model.UserSecurity;

@Repository
@RequiredArgsConstructor
public class JpaUserSecurityService implements UserDetailsService {
    private final UsersRepository usersRepository;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return  usersRepository.findByEmail(email).map(UserSecurity::new).orElseThrow(()->new UsernameNotFoundException("User Not Found"));
    }
}
