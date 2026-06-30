package lk.spring_security.refresh_token.infrastructure._security.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lk.spring_security.refresh_token.infrastructure._security.token_extraction.TokenExtractor;
import org.jspecify.annotations.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    //inject required dependencies
    private final TokenExtractor tokenExtractor;

    private final UserDetailsService userDetailsService;

    public JwtAuthenticationFilter(
            TokenExtractor tokenExtractor,

            UserDetailsService userDetailsService
    ) {
        this.tokenExtractor = tokenExtractor;

        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {

        //get access token from cookie
        final String accessToken = tokenExtractor.extractTokenFromCookie(request).orElse(null) ;
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
            //get user details on db
            UserDetails userDetails = userDetailsService.loadUserByUsername(userEmail);

            //check token validation
            if (tokenService.isTokenValid(accessToken, userDetails)) {

                //create authentication object
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                );

                authToken.setDetails(new WebAuthenticationDetails(request));

                //set object to security context
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        filterChain.doFilter(request, response);
    }
}
