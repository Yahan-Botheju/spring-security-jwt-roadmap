package lk.spring_security.cookie_based_jwt_auth.usecase.user;

import lk.spring_security.cookie_based_jwt_auth.domain.repositories.UserRepository;

public class UserUseCaseImpl implements UserUseCase{

    //inject required classes
    private final UserRepository userRepository;

    public UserUseCaseImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
