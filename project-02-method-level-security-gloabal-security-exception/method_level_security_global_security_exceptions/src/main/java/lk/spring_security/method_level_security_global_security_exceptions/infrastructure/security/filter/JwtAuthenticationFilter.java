package lk.spring_security.method_level_security_global_security_exceptions.infrastructure.security.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lk.spring_security.method_level_security_global_security_exceptions.infrastructure.security.JwtImpl;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    //inject required classes as constructor injection
    private final JwtImpl jwtImpl;
    private final UserDetailsService userDetailsService;

    public JwtAuthenticationFilter(JwtImpl jwtImpl, UserDetailsService userDetailsService) {
        this.jwtImpl = jwtImpl;
        this.userDetailsService = userDetailsService;
    }

    //initiate filter method
    @Override
    protected void doFilterInternal(
            @NotNull HttpServletRequest request,
            @NotNull HttpServletResponse response,
            @NotNull FilterChain filterChain
    ) throws ServletException, IOException {

        final String authorizationHeader = request.getHeader("Authorization");
        final String jwtToken;
        final String userEmail;

        //check token then set to next filter
        if(authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")){
            filterChain.doFilter(request, response);
        }

        //separate token from auth header
        jwtToken = authorizationHeader.substring(7);

        //separate username from token
        userEmail = jwtImpl.extractUsername(jwtToken);
    }
}
