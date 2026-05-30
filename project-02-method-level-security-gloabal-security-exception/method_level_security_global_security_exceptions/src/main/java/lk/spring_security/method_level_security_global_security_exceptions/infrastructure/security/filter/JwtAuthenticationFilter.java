package lk.spring_security.method_level_security_global_security_exceptions.infrastructure.security.filter;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
}
