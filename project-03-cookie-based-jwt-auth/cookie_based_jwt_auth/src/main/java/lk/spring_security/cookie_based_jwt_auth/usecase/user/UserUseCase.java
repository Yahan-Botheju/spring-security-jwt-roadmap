package lk.spring_security.cookie_based_jwt_auth.usecase.user;

import lk.spring_security.cookie_based_jwt_auth.usecase.user.records.UpdateUserDetailsCommand;
import lk.spring_security.cookie_based_jwt_auth.usecase.user.records.UpdateUserDetailsResult;

public interface UserUseCase {

    //update user
    UpdateUserDetailsResult updateUserDetails(UpdateUserDetailsCommand updateUserDetailsCommand);

    //delete user
    void deleteUser(Long userId);
}
