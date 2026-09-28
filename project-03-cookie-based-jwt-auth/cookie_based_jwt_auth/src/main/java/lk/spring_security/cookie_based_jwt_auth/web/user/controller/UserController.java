package lk.spring_security.cookie_based_jwt_auth.web.user.controller;

import jakarta.validation.Valid;
import lk.spring_security.cookie_based_jwt_auth.infrastructure._security.user_spring_wrapper.CustomUserDetails;
import lk.spring_security.cookie_based_jwt_auth.usecase.user.UserUseCase;
import lk.spring_security.cookie_based_jwt_auth.usecase.user.records.UpdateUserDetailsCommand;
import lk.spring_security.cookie_based_jwt_auth.usecase.user.records.UpdateUserDetailsResult;
import lk.spring_security.cookie_based_jwt_auth.web.user.DTOs.UpdateUserRequestDTO;
import lk.spring_security.cookie_based_jwt_auth.web.user.DTOs.UpdateUserResponseDTO;
import lk.spring_security.cookie_based_jwt_auth.web.user.webMapper.UserWebMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
    public ResponseEntity<UpdateUserResponseDTO> updateUser(
            @AuthenticationPrincipal CustomUserDetails customUserDetails,
            @Valid @RequestBody UpdateUserRequestDTO updateUserRequestDTO
    ){
        Long userId = customUserDetails.getUserId();

        UpdateUserDetailsCommand toUpdateCommand = userWebMapper.toUpdateUserDetailsCommand(userId,updateUserRequestDTO);
        UpdateUserDetailsResult toResult = userUseCase.updateUserDetails(toUpdateCommand);
        UpdateUserResponseDTO responseDTO = userWebMapper.toUpdateUserResponseDTO(toResult);

        return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
    }

    //delete user
    @DeleteMapping("/profile")
    public ResponseEntity<Void> deleteUser(
            @AuthenticationPrincipal CustomUserDetails customUserDetails
    ){
        Long userId = customUserDetails.getUserId();
        userUseCase.deleteUser(userId);

        return ResponseEntity.ok().build();
    }


}
