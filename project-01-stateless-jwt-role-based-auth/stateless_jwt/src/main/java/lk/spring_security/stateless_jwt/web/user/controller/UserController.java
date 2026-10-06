package lk.spring_security.stateless_jwt.web.user.controller;

import lk.spring_security.stateless_jwt.usecase.user.UserUseCase;
import lk.spring_security.stateless_jwt.usecase.user.records.*;
import lk.spring_security.stateless_jwt.web.user.DTOs.*;
import lk.spring_security.stateless_jwt.web.user.webMappers.UserWebMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    //inject required dependencies
    private final UserUseCase userUseCase;
    private final UserWebMapper userWebMapper;

    //get user profile
    @GetMapping("/profile")
    public ResponseEntity<GetUserProfileResponseDTO> getUserProfile(
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        // turn to command
        GetUserProfileCommand toCommand = userWebMapper.toGetUserProfileCommand(userDetails.getUsername());
        // obj set to usecase
        GetUserProfileResult toResult = userUseCase.userProfile(toCommand);
        // turn to response
        GetUserProfileResponseDTO  toResponseDTO = userWebMapper.toGetUserProfileResponseDTO(toResult);

        return ResponseEntity.status(HttpStatus.OK).body(toResponseDTO);
    }

    //update user profile
    @PutMapping("/profile")
    public ResponseEntity<UpdateUserProfileResponseDTO> updateUserProfile(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestBody UpdateUserProfileRequestDTO updateUserProfileRequestDTO
    ){
        UpdateUserProfileCommand toCommand =  userWebMapper
                .toUpdateUserProfileCommand(userDetails.getUsername(), updateUserProfileRequestDTO);
        UpdateUserProfileResult toResult = userUseCase.updateProfile(toCommand);
        UpdateUserProfileResponseDTO responseDTO = userWebMapper.toUpdateUserProfileResponseDTO(toResult);

        return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
    }

    //delete user
    @DeleteMapping("/profile")
    public ResponseEntity<String> deleteUser(
            @AuthenticationPrincipal UserDetails userDetails
    ){
        DeleteUserCommand toCommand = userWebMapper.toDeleteUserCommand(userDetails.getUsername());
        userUseCase.deleteUser(toCommand);

        return ResponseEntity.ok(" user deleted successfully" + " , " +  toCommand.email());
    }
}
