package lk.spring_security.method_level_security_global_security_exceptions.web.user.controllers;

import jakarta.validation.Valid;
import lk.spring_security.method_level_security_global_security_exceptions.infrastructure.security.user.CustomUserDetails;
import lk.spring_security.method_level_security_global_security_exceptions.usecase.user.UserUseCase;
import lk.spring_security.method_level_security_global_security_exceptions.web.user.DTOs.UserRequestDTO;
import lk.spring_security.method_level_security_global_security_exceptions.web.user.DTOs.UserResponseDTO;
import lk.spring_security.method_level_security_global_security_exceptions.web.user.webMappers.UserWebMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
    @PutMapping("/profile")
    public ResponseEntity<UserResponseDTO> updateUser(
            @Valid @RequestBody UserRequestDTO userRequestDTO,
            @AuthenticationPrincipal CustomUserDetails customUserDetails
    ){
        Long userId = customUserDetails.getUserId();
        UserResponseDTO responseDTO = userWebMapper.toResponseDTO(
                userUseCase.updateUser(
                        userId,userWebMapper.toDomainModel(userRequestDTO))  );

        return ResponseEntity.ok(responseDTO);
    }

    //delete user profile
    @DeleteMapping("/profile")
    public ResponseEntity<String> deleteUser(
            @Valid @AuthenticationPrincipal CustomUserDetails customUserDetails
    ){
        Long userId = customUserDetails.getUserId();
        userUseCase.deleteUser(userId);

        return ResponseEntity.ok("User delete successfully" + " , " +  userId);
    }
}
