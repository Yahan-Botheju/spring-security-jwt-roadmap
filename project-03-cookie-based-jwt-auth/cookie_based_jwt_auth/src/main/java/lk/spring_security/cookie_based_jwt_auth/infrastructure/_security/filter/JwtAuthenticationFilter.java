package lk.spring_security.cookie_based_jwt_auth.infrastructure._security.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lk.spring_security.cookie_based_jwt_auth.domain.services.JwtService;
import lk.spring_security.cookie_based_jwt_auth.infrastructure._security.token_extraction.TokenExtractor;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    //inject required classes
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;
    private final TokenExtractor tokenExtractor;

    public JwtAuthenticationFilter(
            JwtService jwtService,
            UserDetailsService userDetailsService,
            TokenExtractor tokenExtractor
    ) {
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
        this.tokenExtractor = tokenExtractor;
    }


    @Override
    protected void doFilterInternal(
            @NotNull HttpServletRequest request,
            @NotNull HttpServletResponse response,
            @NotNull FilterChain filterChain
    ) throws ServletException, IOException {

        //get token from cookie
        String jwt = tokenExtractor.extractTokenFromCookie(request).orElse(null);
        String userEmail = null;

        //extract username using jwt service username extract method
        if(jwt != null) {
            userEmail = jwtService.extractUserName(jwt);
        }

        //check user email is not empty and spring security context is empty to store user
        if(userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            //get user details from userDetails service then wrap using UserDetails
            UserDetails userDetails = userDetailsService.loadUserByUsername(userEmail);

        }

    }
}
