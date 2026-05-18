package lk.spring_security.stateless_jwt.usecase.user;

import lk.spring_security.stateless_jwt.domain.repositories.UserRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserUseCaseImpl implements  UserUseCase {

    //inject user domain repo
    private final UserRepository userRepository;

}
