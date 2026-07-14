package lk.spring_security.stateful_jwt_refresh_token_rotation.infrastructure._security.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lk.spring_security.stateful_jwt_refresh_token_rotation.domain.repositories.CookieService;
import lk.spring_security.stateful_jwt_refresh_token_rotation.domain.repositories.TokenService;
import org.jspecify.annotations.NonNull;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    //inject required dependencies
    private final CookieService cookieService;
    private final TokenService tokenService;
    private final UserDetailsService userDetailsService;

    public JwtAuthenticationFilter(
            CookieService cookieService,
            TokenService tokenService,
            UserDetailsService userDetailsService
    ) {
        this.cookieService = cookieService;
        this.tokenService = tokenService;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {

        //get header from request
        final String authorizationHeader = request.getHeader("Authorization");
        final String jwt;
        final String userEmail;

        //check header and pass request next filter
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        //separate token from header
        jwt = authorizationHeader.substring(7);
        //separate user email from token
        userEmail = tokenService.extractUsername(jwt);

        //check user email is available and spring security context is empty
        if(userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {

        }
    }
}
