package lk.spring_security.stateful_jwt_refresh_token_rotation.web.auth.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lk.spring_security.stateful_jwt_refresh_token_rotation.domain.models.User;
import lk.spring_security.stateful_jwt_refresh_token_rotation.domain.records.AuthenticatedUser;
import lk.spring_security.stateful_jwt_refresh_token_rotation.domain.repositories.CookieService;
import lk.spring_security.stateful_jwt_refresh_token_rotation.usecase.LoginUserUseCase;
import lk.spring_security.stateful_jwt_refresh_token_rotation.usecase.RefreshTokenUseCase;
import lk.spring_security.stateful_jwt_refresh_token_rotation.usecase.RegisterUserUseCase;
import lk.spring_security.stateful_jwt_refresh_token_rotation.usecase.auth.AuthUseCase;
import lk.spring_security.stateful_jwt_refresh_token_rotation.usecase.records.RefreshTokenResult;
import lk.spring_security.stateful_jwt_refresh_token_rotation.usecase.records.RegisterUseCommand;
import lk.spring_security.stateful_jwt_refresh_token_rotation.usecase.records.RegisterUserResult;
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

    public AuthController(
            AuthUseCase authUseCase,
            AuthWebMapper authWebMapper,
            CookieService cookieService,
            RefreshTokenUseCase refreshTokenUseCase,
            RegisterUserUseCase registerUserUseCase,
            LoginUserUseCase loginUserUseCase
    ) {
        this.authUseCase = authUseCase;
        this.authWebMapper = authWebMapper;
        this.cookieService = cookieService;
        this.refreshTokenUseCase = refreshTokenUseCase;
        this.registerUserUseCase = registerUserUseCase;
        this.loginUserUseCase = loginUserUseCase;
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
            @Valid @RequestBody LoginUserRequestDTO authRequestDTO,
            HttpServletResponse httpServletResponse
    ){
        //get token
        AuthenticatedUser authenticatedUser = authUseCase.loginUser(
                authRequestDTO.getEmail(),
                authRequestDTO.getPassword(),
                httpServletResponse);

        LoginUserResponseDTO responseDTO = authWebMapper.toResponse(authenticatedUser);

        return ResponseEntity.ok(responseDTO);
    }

    //logout endpoint
    @PostMapping("/logout")
    public ResponseEntity<String> logout(
            HttpServletResponse httpServletResponse
    ){
        authUseCase.logout(httpServletResponse);

        return new ResponseEntity<>("Logout successfully", HttpStatus.OK);
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
