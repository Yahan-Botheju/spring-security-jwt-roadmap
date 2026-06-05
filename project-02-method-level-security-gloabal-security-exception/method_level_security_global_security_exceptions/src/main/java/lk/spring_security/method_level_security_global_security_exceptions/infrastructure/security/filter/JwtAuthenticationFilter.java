package lk.spring_security.method_level_security_global_security_exceptions.infrastructure.security.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotNull;
import lk.spring_security.method_level_security_global_security_exceptions.domain.models.User;
import lk.spring_security.method_level_security_global_security_exceptions.domain.services.JwtService;
import lk.spring_security.method_level_security_global_security_exceptions.infrastructure.security.JwtImpl;
import lk.spring_security.method_level_security_global_security_exceptions.infrastructure.security.user.CustomUserDetails;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    //inject required classes as constructor injection
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    public JwtAuthenticationFilter(JwtService jwtService, UserDetailsService userDetailsService) {
        this.jwtService = jwtService;
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
            return;
        }

        //separate token from auth header
        jwtToken = authorizationHeader.substring(7).trim();

        System.out.println("jwtToken:"+jwtToken);

        //separate username from token
        userEmail = jwtService.extractUsername(jwtToken);

        //check email availability and user does noe authenticated
        if(userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            //get user details via spring
            UserDetails userDetails = userDetailsService.loadUserByUsername(userEmail);

            //cast userdetails to customUserDetails and get domain model
             if(userDetails instanceof CustomUserDetails customUserDetails){
                 User domainUser = customUserDetails.getUser();
                 //check token is valid
                 if(jwtService.isTokenValid(jwtToken, domainUser)) {

                     //create new authentication object
                     UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                             userDetails,null, userDetails.getAuthorities()
                     );

                     //allow authenticated user to access
                     SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                 }
             }


        }
        //set request to next route
        filterChain.doFilter(request, response);
    }
}
