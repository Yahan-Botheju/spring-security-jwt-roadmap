package lk.spring_security.refresh_token.web.auth.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lk.spring_security.refresh_token.domain.repositories.CookieService;
import lk.spring_security.refresh_token.usecase.auth.*;
import lk.spring_security.refresh_token.usecase.auth.records.*;
import lk.spring_security.refresh_token.web.auth.DTOs.*;
import lk.spring_security.refresh_token.web.auth.webMappers.AuthWebMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    //inject required dependencies
    private final RegisterUseCase registerUseCase;
    private final LoginUseCase loginUseCase;
    private final LogoutUseCase logoutUseCase;
    private final RefreshTokenUseCase refreshTokenUseCase;
    private final AuthWebMapper authWebMapper;
    private final CookieService cookieService;


    public AuthController(
            RegisterUseCase registerUseCase,
            LoginUseCase loginUseCase,
            LogoutUseCase logoutUseCase,
            RefreshTokenUseCase refreshTokenUseCase,
            AuthWebMapper authWebMapper,
            CookieService cookieService
    ) {
        this.registerUseCase = registerUseCase;
        this.loginUseCase = loginUseCase;
        this.logoutUseCase = logoutUseCase;
        this.refreshTokenUseCase = refreshTokenUseCase;
        this.authWebMapper = authWebMapper;
        this.cookieService = cookieService;
    }

    //register user
    @PostMapping("/register")
    public ResponseEntity<RegisterResponseDTO> register(
            @Valid @RequestBody RegisterRequestDTO registerRequestDTO
    ){
        RegisterCommand toCommand = authWebMapper.toRegisterCommand(registerRequestDTO);
        RegisterResult toUseCase = registerUseCase.register(toCommand);
        RegisterResponseDTO responseDTO = authWebMapper.toRegisterResponseDTO(toUseCase);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    //login route
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(
            @Valid @RequestBody LoginRequestDTO loginRequestDTO,
            HttpServletResponse servletResponse
    ){
         LoginCommand toCommand = authWebMapper.toLoginCommand(loginRequestDTO);
         LoginResult toLoginResult = loginUseCase.login(toCommand);

         //set token to secure cookies
         cookieService.setAccessTokenCookie(servletResponse, toLoginResult.accessToken());
         cookieService.setRefreshTokenCookie(servletResponse, toLoginResult.refreshToken());

         LoginResponseDTO responseSTO = authWebMapper.toLoginResponseDTO(toLoginResult);

         return ResponseEntity.status(HttpStatus.OK).body(responseSTO);
    }

    //logout route
    @PostMapping("/logout")
    public ResponseEntity<LogoutResponseDTO> logout(
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse
    ){
        String refreshToken = cookieService.extractCookieByName(httpServletRequest, "refresh_token");
        LogoutRequestDTO logoutRequestDTO  = new LogoutRequestDTO(refreshToken);

        LogoutCommand logoutCommand = authWebMapper.toLogoutCommand(logoutRequestDTO);
        LogoutResult logoutResult = logoutUseCase.logout(logoutCommand);

        cookieService.clearCookies(httpServletResponse);
        SecurityContextHolder.clearContext();

        LogoutResponseDTO responseSTO = authWebMapper.toLogoutResponseDTO(logoutResult);

        return ResponseEntity.status(HttpStatus.OK).body(responseSTO);

    }

    //REFRESH ENDPOINT ROUTE
    @PostMapping("/refresh-token")
    public ResponseEntity<String> refreshToken(
            HttpServletRequest servletRequest,
            HttpServletResponse servletResponse
    ){
        String refreshToken = cookieService.extractCookieByName(servletRequest, "refresh_token");
        RefreshTokenRequestDTO refreshTokenRequestDTO = new RefreshTokenRequestDTO(refreshToken);

        RefreshTokenCommand refreshTokenCommand = authWebMapper.toRefreshTokenCommand(refreshTokenRequestDTO);
        RefreshTokenResult refreshTokenResult =

    }
}
