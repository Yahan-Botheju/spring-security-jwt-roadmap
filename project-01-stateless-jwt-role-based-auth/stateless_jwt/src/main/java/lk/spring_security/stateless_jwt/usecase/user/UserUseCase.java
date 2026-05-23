package lk.spring_security.stateless_jwt.usecase.user;

import lk.spring_security.stateless_jwt.domain.models.User;

public interface UserUseCase {

    //user profile
    User getUserProfile(String email);
}
