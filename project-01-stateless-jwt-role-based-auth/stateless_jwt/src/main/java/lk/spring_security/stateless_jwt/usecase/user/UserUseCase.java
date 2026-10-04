package lk.spring_security.stateless_jwt.usecase.user;

import lk.spring_security.stateless_jwt.usecase.user.records.GetUserProfileCommand;
import lk.spring_security.stateless_jwt.usecase.user.records.GetUserProfileResult;
import lk.spring_security.stateless_jwt.usecase.user.records.UpdateUserProfileCommand;
import lk.spring_security.stateless_jwt.usecase.user.records.UpdateUserProfileResult;

public interface UserUseCase {

    //delete user
    void deleteUser(String email);

    //get user profile
    GetUserProfileResult userProfile(GetUserProfileCommand getUserProfileCommand);

    //update user profile
    UpdateUserProfileResult updateProfile(UpdateUserProfileCommand updateUserProfileCommand);
}
