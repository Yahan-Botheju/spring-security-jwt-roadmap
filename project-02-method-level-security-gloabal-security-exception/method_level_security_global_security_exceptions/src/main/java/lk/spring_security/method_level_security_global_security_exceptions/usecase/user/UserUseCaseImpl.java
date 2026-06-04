package lk.spring_security.method_level_security_global_security_exceptions.usecase.user;

import jakarta.transaction.Transactional;
import lk.spring_security.method_level_security_global_security_exceptions.domain.models.User;
import lk.spring_security.method_level_security_global_security_exceptions.domain.repositories.UserRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserUseCaseImpl implements UserUseCase {

    //inject user repo
    private final UserRepository userRepository;

    //create constructor
    public UserUseCaseImpl(
            UserRepository userRepository
    ) {
        this.userRepository = userRepository;
    }

    //update user
    @Override
    @Transactional
    public User updateUser(Long userId,  User user) {
        User exsitingUser = userRepository.findById(userId)
                .orElseThrow(() -> new UsernameNotFoundException("user not found" + " , " +  userId));

        if(user.getEmail() != null && !user.getEmail().isEmpty()){
            exsitingUser.setEmail(user.getEmail());
        }
        return userRepository.updateUser(exsitingUser);
    }

    //delete user
    @Override
    public void deleteUser(Long userId) {
        User existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new UsernameNotFoundException("user not found" + " , " +  userId));
        userRepository.deleteUser(existingUser);
    }
}
