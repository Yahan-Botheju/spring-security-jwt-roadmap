package lk.spring_security.method_level_security_global_security_exceptions.infrastructure.security.user;

import lk.spring_security.method_level_security_global_security_exceptions.domain.models.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;

@RequiredArgsConstructor
public class CustomUserDetails implements UserDetails {

    private final User user;



}
