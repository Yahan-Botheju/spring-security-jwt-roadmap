package lk.spring_security.stateful_jwt_refresh_token_rotation.web.auth.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lk.spring_security.stateful_jwt_refresh_token_rotation.domain.models.User;
import lk.spring_security.stateful_jwt_refresh_token_rotation.domain.records.AuthenticatedUser;
import lk.spring_security.stateful_jwt_refresh_token_rotation.domain.repositories.CookieService;
import lk.spring_security.stateful_jwt_refresh_token_rotation.usecase.LoginUserUseCase;
import lk.spring_security.stateful_jwt_refresh_token_rotation.usecase.LogoutUserUseCase;
import lk.spring_security.stateful_jwt_refresh_token_rotation.usecase.RefreshTokenUseCase;
import lk.spring_security.stateful_jwt_refresh_token_rotation.usecase.RegisterUserUseCase;
import lk.spring_security.stateful_jwt_refresh_token_rotation.usecase.auth.AuthUseCase;
import lk.spring_security.stateful_jwt_refresh_token_rotation.usecase.records.*;
import lk.spring_security.stateful_jwt_refresh_token_rotation.web.auth.DTOs.*;
import lk.spring_security.stateful_jwt_refresh_token_rotation.web.auth.webMapper.AuthWebMapper;
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
    private final AuthWebMapper authWebMapper;
    private final CookieService cookieService;

    private final RefreshTokenUseCase refreshTokenUseCase;
    private final RegisterUserUseCase registerUserUseCase;
    private final LoginUserUseCase loginUserUseCase;
    private final LogoutUserUseCase logoutUserUseCase;

    public AuthController(
            AuthUseCase authUseCase,
            AuthWebMapper authWebMapper,
            CookieService cookieService,
            RefreshTokenUseCase refreshTokenUseCase,
            RegisterUserUseCase registerUserUseCase,
            LoginUserUseCase loginUserUseCase,
            LogoutUserUseCase logoutUserUseCase
    ) {
        this.authUseCase = authUseCase;
        this.authWebMapper = authWebMapper;
        this.cookieService = cookieService;
        this.refreshTokenUseCase = refreshTokenUseCase;
        this.registerUserUseCase = registerUserUseCase;
        this.loginUserUseCase = loginUserUseCase;
        this.logoutUserUseCase = logoutUserUseCase;
    }

    //register endpoint
    @PostMapping("/register")
    public ResponseEntity<RegisterUserResponseDTO> register(
            @Valid @RequestBody RegisterUserRequestDTO registerUserRequestDTO
            ){

        RegisterUseCommand toCommand = authWebMapper.toRegisterUserCommand(registerUserRequestDTO);
        RegisterUserResult toUseCase = registerUserUseCase.registerUser(toCommand);
        RegisterUserResponseDTO responseDTO = authWebMapper.toRegisterUserResponse(toUseCase);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    //login endpoint
    @PostMapping("/login")
    public ResponseEntity<LoginUserResponseDTO> login(
            @Valid @RequestBody LoginUserRequestDTO loginUserRequestDTO,
            HttpServletResponse httpServletResponse
    ){
        LoginUserCommand command = authWebMapper.toLoginUserCommand(loginUserRequestDTO);
        LoginUserResult toUseCase = loginUserUseCase.loginUser(command);
        LoginUserResponseDTO responseDTO = authWebMapper.toLoginUserResponse(toUseCase);

        //call cookie service in controller
        cookieService.addRefreshTokenCookie(httpServletResponse, toUseCase.refreshToken());

        return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
    }

    //logout endpoint
    @PostMapping("/logout")
    public ResponseEntity<Void> logout(
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse
    ){
        String refreshToken = cookieService.extractRefreshTokenFromCookie(httpServletRequest);

        if(refreshToken != null){
            logoutUserUseCase.logout(refreshToken);
        }

        cookieService.clearCookie(httpServletResponse);

        return ResponseEntity.noContent().build();
    }

    //refresh token route
    @PostMapping("/refresh-token")
    public ResponseEntity<RefreshTokenResponseDTO> refreshToken(
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse
    ) {

        String refreshToken = cookieService.extractRefreshTokenFromCookie(httpServletRequest);

        RefreshTokenResult refreshTokenResult = refreshTokenUseCase.refreshToken(refreshToken);
        cookieService.addRefreshTokenCookie(httpServletResponse, refreshTokenResult.newRefreshToken());

        RefreshTokenResponseDTO responseDTO = authWebMapper.toRefreshTokenResponse(refreshTokenResult);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(responseDTO);
    }

}
