package lk.spring_security.method_level_security_global_security_exceptions.infrastructure.security.filter;

import lk.spring_security.method_level_security_global_security_exceptions.infrastructure.security.JwtImpl;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    //inject required classes as constructor injection
    private final JwtImpl jwtImpl;
    private final UserDetailsService userDetailsService;

    public JwtAuthenticationFilter(JwtImpl jwtImpl, UserDetailsService userDetailsService) {
        this.jwtImpl = jwtImpl;
        this.userDetailsService = userDetailsService;
    }
}
