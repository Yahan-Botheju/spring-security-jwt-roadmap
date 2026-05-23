package lk.spring_security.stateless_jwt.web.user.Controller;

import lk.spring_security.stateless_jwt.domain.models.User;
import lk.spring_security.stateless_jwt.usecase.user.UserUseCase;
import lk.spring_security.stateless_jwt.web.user.DTOs.UserRequestDTO;
import lk.spring_security.stateless_jwt.web.user.DTOs.UserResponseDTO;
import lk.spring_security.stateless_jwt.web.user.webMappers.UserWebMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    //inject user usecase
    private final UserUseCase userUseCase;

    //inject user web mapper
    private final UserWebMapper userWebMapper;

    //get user profile
    @GetMapping("/profile")
    public ResponseEntity<UserResponseDTO> getUserProfile(
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        String email = userDetails.getUsername();

        User user = userUseCase.getUserProfile(email);
        UserResponseDTO responseDTO = userWebMapper.toResponseDTO(user);
        return ResponseEntity.ok(responseDTO);
    }

    //update user profile
    @PutMapping("/profile")
    public ResponseEntity<UserResponseDTO> updateUserProfile(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestBody UserRequestDTO userRequestDTO
    ){
        String email = userDetails.getUsername();

        UserResponseDTO responseDTO = userWebMapper.toResponseDTO(
                userUseCase.updateUser(
                        userWebMapper.toDomainModel(
                                userRequestDTO),email));

        return ResponseEntity.ok(responseDTO);
    }
}
