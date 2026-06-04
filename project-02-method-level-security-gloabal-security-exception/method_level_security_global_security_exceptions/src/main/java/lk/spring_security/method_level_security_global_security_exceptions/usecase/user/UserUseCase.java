package lk.spring_security.method_level_security_global_security_exceptions.usecase.user;

import lk.spring_security.method_level_security_global_security_exceptions.domain.models.User;

public interface UserUseCase {

    //update user
    User updateUser(Long userId, User user);

    //delete user
    void deleteUser(Long userId);
}
