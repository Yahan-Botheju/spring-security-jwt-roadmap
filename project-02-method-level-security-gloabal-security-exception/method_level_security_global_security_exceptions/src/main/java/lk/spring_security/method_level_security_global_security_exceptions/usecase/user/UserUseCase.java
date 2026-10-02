package lk.spring_security.method_level_security_global_security_exceptions.usecase.user;

import lk.spring_security.method_level_security_global_security_exceptions.usecase.user.records.UpdateUserCommand;

public interface UserUseCase {

    //update user
    UpdateUserCommand updateUser(UpdateUserCommand updateUserCommand);

    //delete user
    void deleteUser(Long userId);
}
