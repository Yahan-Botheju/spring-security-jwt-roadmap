package lk.spring_security.stateful_jwt_refresh_token_rotation.infrastructure._security.filter;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
}
