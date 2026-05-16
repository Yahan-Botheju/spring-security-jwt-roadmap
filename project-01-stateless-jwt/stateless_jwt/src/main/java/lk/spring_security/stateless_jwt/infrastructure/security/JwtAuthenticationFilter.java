package lk.spring_security.stateless_jwt.infrastructure.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    //inject JWT service
    private final JwtService jwtService;

    //inject user service details
    private final UserDetailsService userDetailsService;

    //initiate filter method
    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {

        final String authHeader = request.getHeader("Authorization");
        final String jwtToken;
        final String userEmail;

        //if token is empty or not, start with Bearer then set to next filter
        if(authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        //separate token from auth header (Bearer)
        jwtToken = authHeader.substring(7);

        //check name in token with jwtService method
        userEmail = jwtService.extractUserName(jwtToken);

        //check user has email and user ain't authenticate
        if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            //get user details using interface
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);

            //check token is valid
            if(jwtService.isTokenValid(jwtToken, userDetails)) {

                //create new Authentication object
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                );
            }

        }
    }
}
