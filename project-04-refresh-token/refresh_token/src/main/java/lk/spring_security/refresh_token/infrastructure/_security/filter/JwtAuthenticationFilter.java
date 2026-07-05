package lk.spring_security.refresh_token.infrastructure._security.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lk.spring_security.refresh_token.domain.repositories.CookieService;
import lk.spring_security.refresh_token.domain.repositories.TokenService;
import org.jspecify.annotations.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
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

        //get access token from cookie
        final String accessToken = cookieService.extractCookieByName(request, "accessToken");
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

                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                //set object to security context
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }else{
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("Token invalid");
                return;
            }
        }
        filterChain.doFilter(request, response);
    }
}
