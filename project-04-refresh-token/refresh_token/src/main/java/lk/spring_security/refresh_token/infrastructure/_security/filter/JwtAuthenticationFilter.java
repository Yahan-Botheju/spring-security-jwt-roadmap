package lk.spring_security.refresh_token.infrastructure._security.filter;

import lk.spring_security.refresh_token.domain.repositories.TokenService;
import lk.spring_security.refresh_token.web._shared.services.CookieService;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    //inject required dependencies
    private final TokenService tokenService;
    private final CookieService cookieService;
    private final UserDetailsService userDetailsService;

    public JwtAuthenticationFilter(
            TokenService tokenService,
            CookieService cookieService,
            UserDetailsService userDetailsService
    ) {
        this.tokenService = tokenService;
        this.cookieService = cookieService;
        this.userDetailsService = userDetailsService;
    }
}
