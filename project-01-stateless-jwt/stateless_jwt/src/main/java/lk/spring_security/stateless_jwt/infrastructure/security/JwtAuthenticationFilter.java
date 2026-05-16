package lk.spring_security.stateless_jwt.infrastructure.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    //inject JWT service
    private final JwtService jwtService;

    //inject user service details
    private final UserDetailsService userDetailsService;
}
