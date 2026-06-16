package lk.spring_security.cookie_based_jwt_auth.web.user.controller;

import jakarta.validation.Valid;
import lk.spring_security.cookie_based_jwt_auth.domain.models.User;
import lk.spring_security.cookie_based_jwt_auth.infrastructure._security.user_spring_wrapper.CustomUserDetails;
import lk.spring_security.cookie_based_jwt_auth.usecase.user.UserUseCase;
import lk.spring_security.cookie_based_jwt_auth.web.user.DTOs.UserRequestDTO;
import lk.spring_security.cookie_based_jwt_auth.web.user.DTOs.UserResponseDTO;
import lk.spring_security.cookie_based_jwt_auth.web.user.webMapper.UserWebMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    //inject required class
    private final UserUseCase userUseCase;
    private final UserWebMapper userWebMapper;

    public UserController(
            UserUseCase userUseCase,
            UserWebMapper userWebMapper
    ) {
        this.userUseCase = userUseCase;
        this.userWebMapper = userWebMapper;
    }

    //update user
    @PutMapping("/profile")
    public ResponseEntity<UserResponseDTO> updateUser(
            @AuthenticationPrincipal CustomUserDetails customUserDetails,
            @Valid @RequestBody UserRequestDTO userRequestDTO
            ){
        Long userId = customUserDetails.getUserId();

        User toDomainModel = userWebMapper.toDomainModel(userRequestDTO);
        User toUseCase = userUseCase.updateUser(userId, toDomainModel);
        UserResponseDTO responseDTO = userWebMapper.toResponseDTO(toUseCase);

        return ResponseEntity.ok(responseDTO);
    }
}
