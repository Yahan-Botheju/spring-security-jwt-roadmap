package lk.spring_security.method_level_security_global_security_exceptions.usecase.user;

import lk.spring_security.method_level_security_global_security_exceptions.usecase.user.records.UpdateUserCommand;
import lk.spring_security.method_level_security_global_security_exceptions.usecase.user.records.UpdateUserResult;

public interface UserUseCase {

    //update user
    UpdateUserResult updateUser(UpdateUserCommand updateUserCommand);

    //delete user
    void deleteUser(Long userId);
}
