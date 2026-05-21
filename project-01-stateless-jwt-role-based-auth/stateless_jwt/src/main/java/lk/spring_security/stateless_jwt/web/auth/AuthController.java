package lk.spring_security.stateless_jwt.web.auth;

import lk.spring_security.stateless_jwt.usecase.auth.AuthUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    //inject auth usecase
    private final AuthUseCase authUseCase;

    //register new user
    public ResponseEntity<AuthResponseDTO> register(
            @RequestBody AuthRequestDTO authRequestDTO
    ){
        return  ResponseEntity.ok(authUseCase.register(authRequestDTO));
    }


}
