package lk.spring_security.stateless_jwt.usecase.user;

import lk.spring_security.stateless_jwt.domain.models.User;
import lk.spring_security.stateless_jwt.domain.repositories.UserRepository;
import lk.spring_security.stateless_jwt.web.user.DTOs.UserResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

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

    //update user profile
    @Override
    @Transactional
    public User updateUser(User user,String currentEmail){
        User checkUser = userRepository.userFindByEmail(currentEmail)
                .orElseThrow(() ->  new IllegalArgumentException("User not found"));

        //check user has given new email
        if(user.getEmail() != null && !user.getEmail().isEmpty()){
            checkUser.setEmail(user.getEmail());
        }

        return userRepository.updateUser(checkUser);
    }
}
