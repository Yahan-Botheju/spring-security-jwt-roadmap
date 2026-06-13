package lk.spring_security.cookie_based_jwt_auth.web.auth.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lk.spring_security.cookie_based_jwt_auth.domain.models.User;
import lk.spring_security.cookie_based_jwt_auth.domain.services.CookieService;
import lk.spring_security.cookie_based_jwt_auth.usecase.auth.AuthUseCase;
import lk.spring_security.cookie_based_jwt_auth.web._shared.services.HttpCookieProvider;
import lk.spring_security.cookie_based_jwt_auth.web.auth.DTOs.AuthRequestDTO;
import lk.spring_security.cookie_based_jwt_auth.web.auth.DTOs.AuthResponseDTO;
import lk.spring_security.cookie_based_jwt_auth.web.auth.webMapper.AuthWebMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    //inject required classes
    private final AuthUseCase authUseCase;
    private final HttpCookieProvider httpCookieProvider;
    private final AuthWebMapper authWebMapper;

    public AuthController(
            AuthUseCase authUseCase,
            HttpCookieProvider httpCookieProvider,
            AuthWebMapper authWebMapper
    ) {
        this.authUseCase = authUseCase;
        this.httpCookieProvider = httpCookieProvider;
        this.authWebMapper = authWebMapper;
    }

    //register user
    @PostMapping("/register")
    public ResponseEntity<AuthResponseDTO> registerUser(
            @Valid @RequestBody AuthRequestDTO authRequestDTO,
            HttpServletResponse response //set cookie to get response
            ){
        User toDomainModel = authWebMapper.toDomainModel(authRequestDTO);
        //generate token
        String token = authUseCase.registerUser(toDomainModel);

        //token set as http only cookie
        httpCookieProvider.setAuthCookie(response,token);

        return ResponseEntity.ok(
                new AuthResponseDTO(
                        "SUCCESS",
                        "User registered successfully"
                )
        );
    }

    //login user
    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> loginUser(
            @Valid @RequestBody AuthRequestDTO authRequestDTO,
            HttpServletResponse response //get response object to set cookie
    ){
        //check credentials and get token
        String token = authUseCase.loginUser(authRequestDTO.getEmail(),  authRequestDTO.getPassword());

        //set token as HttpOnly Cookie to browser
        httpCookieProvider.setAuthCookie(response,token);

        return ResponseEntity.ok(
                new AuthResponseDTO(
                        "SUCCESS",
                        "User logged in successfully"
                )
        );
    }

    //logout user
    @PostMapping("/logout")
    public ResponseEntity<AuthResponseDTO> logoutUser(
            HttpServletResponse response //get response object to set cookie
    ){

    }
}
