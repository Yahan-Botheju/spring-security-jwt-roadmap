package lk.spring_security.method_level_security_global_security_exceptions.infrastructure.security.config.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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

        //create error body
        final Map<String, Object> errorBody = new HashMap<>();
        errorBody.put("status", HttpServletResponse.SC_UNAUTHORIZED);
        errorBody.put("error", "Unauthorized");
        errorBody.put("message", "Invalid or missing token, Access Denied");
        errorBody.put("path", request.getServletPath());

        //turn into JSON String by Jackson object mapper and set to response
        final ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getOutputStream(), errorBody);
    }
}
