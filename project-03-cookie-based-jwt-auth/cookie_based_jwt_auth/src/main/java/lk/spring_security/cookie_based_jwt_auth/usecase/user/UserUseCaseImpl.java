package lk.spring_security.cookie_based_jwt_auth.usecase.user;

import lk.spring_security.cookie_based_jwt_auth.domain.models.User;
import lk.spring_security.cookie_based_jwt_auth.domain.repositories.UserRepository;

public class UserUseCaseImpl implements UserUseCase{

    //inject required classes
    private final UserRepository userRepository;

    public UserUseCaseImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //update user
    @Override
    public User updateUser(Long userId, User user) {
        if(!userRepository.userFindById(userId).isPresent()){
            throw new IllegalArgumentException("User does not exist");
        }
        user.setUserId(userId);
        return userRepository.updateUser(user);
    }

    //delete user
    @Override
    public void deleteUser(Long userId) {
        User existingUser = userRepository.userFindById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        userRepository.deleteUser(existingUser.getUserId());
    }
}
