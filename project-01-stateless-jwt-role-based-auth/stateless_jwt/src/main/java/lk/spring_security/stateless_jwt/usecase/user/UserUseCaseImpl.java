package lk.spring_security.stateless_jwt.usecase.user;

import lk.spring_security.stateless_jwt.domain.models.User;
import lk.spring_security.stateless_jwt.domain.repositories.UserRepository;
import lk.spring_security.stateless_jwt.web.user.DTOs.UserResponseDTO;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserUseCaseImpl implements  UserUseCase {

    //inject user domain repo
    private final UserRepository userRepository;


    //get user profile
    @Override
    public User getUserProfile(String email) {
        return userRepository.userFindByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
    }
}
