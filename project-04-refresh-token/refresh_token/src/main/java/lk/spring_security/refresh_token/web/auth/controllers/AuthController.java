package lk.spring_security.refresh_token.web.auth.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lk.spring_security.refresh_token.domain.repositories.CookieService;
import lk.spring_security.refresh_token.usecase.auth.AuthUseCase;
import lk.spring_security.refresh_token.usecase.auth.LoginUseCase;
import lk.spring_security.refresh_token.usecase.auth.LogoutUseCase;
import lk.spring_security.refresh_token.usecase.auth.RegisterUseCase;
import lk.spring_security.refresh_token.usecase.auth.records.LoginCommand;
import lk.spring_security.refresh_token.usecase.auth.records.LoginResult;
import lk.spring_security.refresh_token.usecase.auth.records.RegisterCommand;
import lk.spring_security.refresh_token.usecase.auth.records.RegisterResult;
import lk.spring_security.refresh_token.web.auth.DTOs.*;
import lk.spring_security.refresh_token.web.auth.webMappers.AuthWebMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    //inject required dependencies
    private final AuthUseCase authUseCase;

    private final RegisterUseCase registerUseCase;
    private final LoginUseCase loginUseCase;
    private final LogoutUseCase logoutUseCase;
    private final AuthWebMapper authWebMapper;
    private final CookieService cookieService;

    public AuthController(
            AuthUseCase authUseCase,

            RegisterUseCase registerUseCase,
            LoginUseCase loginUseCase,
            LogoutUseCase logoutUseCase,
            AuthWebMapper authWebMapper,
            CookieService cookieService
    ) {
        this.authUseCase = authUseCase;

        this.registerUseCase = registerUseCase;
        this.loginUseCase = loginUseCase;
        this.logoutUseCase = logoutUseCase;
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
    public ResponseEntity<String> logoutUser(
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse
    ){

        String refreshToken = cookieService.extractCookieByName(httpServletRequest, "refresh_token");


        authUseCase.logoutUser(httpServletRequest, httpServletResponse);

        return ResponseEntity.ok("User logged out successfully..!!");
    }

    //REFRESH ENDPOINT ROUTE
    @PostMapping("/refresh-token")
    public ResponseEntity<String> refreshToken(
            HttpServletRequest servletRequest,
            HttpServletResponse servletResponse
    ){
        authUseCase.refreshToken(servletRequest, servletResponse);

        return ResponseEntity.ok("Token refresh successfully..!!");
    }
}
