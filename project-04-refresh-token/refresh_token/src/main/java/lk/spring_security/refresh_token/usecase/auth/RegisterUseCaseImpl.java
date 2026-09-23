package lk.spring_security.refresh_token.usecase.auth;

import lk.spring_security.refresh_token.domain.repositories.UserRepository;

public class RegisterUseCaseImpl implements RegisterUseCase {

    //inject required dependencies
    private final UserRepository userRepository;

    public RegisterUseCaseImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
