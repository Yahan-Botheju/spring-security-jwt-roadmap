package lk.spring_security.method_level_security_global_security_exceptions.web.user.controllers;

import lk.spring_security.method_level_security_global_security_exceptions.domain.models.User;
import lk.spring_security.method_level_security_global_security_exceptions.usecase.user.UserUseCase;
import lk.spring_security.method_level_security_global_security_exceptions.web.user.DTOs.UserRequestDTO;
import lk.spring_security.method_level_security_global_security_exceptions.web.user.DTOs.UserResponseDTO;
import lk.spring_security.method_level_security_global_security_exceptions.web.user.webMappers.UserWebMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v2/users")
public class UserController {

    //inject required classes
    private final UserUseCase userUseCase;
    private final UserWebMapper  userWebMapper;

    public UserController(
            UserUseCase userUseCase,
            UserWebMapper userWebMapper
    ) {
        this.userUseCase = userUseCase;
        this.userWebMapper = userWebMapper;
    }

    //update user profile
    @PostMapping("/profile")
    public ResponseEntity<UserResponseDTO> updateUser(
            @RequestBody UserRequestDTO userRequestDTO,
            @AuthenticationPrincipal UserDetails userDetails
    ){
        String userEmail = userDetails.getUsername();
        UserResponseDTO responseDTO = userWebMapper.toResponseDTO(
                userUseCase.updateUser(
                        userEmail,userWebMapper.toDomainModel(userRequestDTO))  );
        return ResponseEntity.ok(responseDTO);
    }
}
