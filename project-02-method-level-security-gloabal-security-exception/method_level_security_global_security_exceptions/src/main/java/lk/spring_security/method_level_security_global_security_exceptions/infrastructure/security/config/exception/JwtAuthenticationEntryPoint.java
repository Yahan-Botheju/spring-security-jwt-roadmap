package lk.spring_security.method_level_security_global_security_exceptions.infrastructure.security.config.exception;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(
            @NotNull HttpServletRequest request,
            HttpServletResponse response,
            @NotNull AuthenticationException authException
    ) throws IOException, ServletException {

        //set response as JSON
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        //401 unauthorized
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

    }
}
