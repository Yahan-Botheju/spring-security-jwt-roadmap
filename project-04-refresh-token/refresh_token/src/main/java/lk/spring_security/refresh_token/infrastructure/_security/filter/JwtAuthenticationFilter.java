package lk.spring_security.refresh_token.infrastructure._security.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lk.spring_security.refresh_token.domain.repositories.TokenService;
import lk.spring_security.refresh_token.web._shared.services.CookieService;
import org.jspecify.annotations.NonNull;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

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

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {

        //get access token from cookie
        final String accessToken = cookieService.extractCookieByName(request, "access_token");
        final String userEmail;

        //token is null do filter
        if (accessToken == null) {
            filterChain.doFilter(request, response);
            return;
        }

        //get username from token
        userEmail = tokenService.extractUsername(accessToken);

        //check email is available and security context is empty
        if(userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {

        }
    }
}
