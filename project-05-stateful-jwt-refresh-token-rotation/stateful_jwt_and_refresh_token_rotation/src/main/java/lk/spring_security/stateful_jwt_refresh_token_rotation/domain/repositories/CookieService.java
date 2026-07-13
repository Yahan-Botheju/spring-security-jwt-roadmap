package lk.spring_security.stateful_jwt_refresh_token_rotation.domain.repositories;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface CookieService {

    //include  refresh token as cookie to response
    void addRefreshTokenCookie(HttpServletResponse httpServletResponse, String refreshToken);

    //extract refresh token cookie from request
    String extractRefreshTokenFromCookie(HttpServletRequest httpServletRequest);

    //clear cookie when logout
    void clearCookie(HttpServletResponse httpServletResponse);
}
