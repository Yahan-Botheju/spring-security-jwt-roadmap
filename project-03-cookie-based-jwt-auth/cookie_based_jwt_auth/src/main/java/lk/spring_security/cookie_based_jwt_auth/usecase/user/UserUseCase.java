package lk.spring_security.cookie_based_jwt_auth.usecase.user;

import lk.spring_security.cookie_based_jwt_auth.domain.models.User;

public interface UserUseCase {

    //update user
    User updateUser(Long userId, User user);
}
