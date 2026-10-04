package lk.spring_security.stateless_jwt.usecase.user;

import lk.spring_security.stateless_jwt.usecase.user.records.*;

public interface UserUseCase {

    //get user profile
    GetUserProfileResult userProfile(GetUserProfileCommand getUserProfileCommand);

    //update user profile
    UpdateUserProfileResult updateProfile(UpdateUserProfileCommand updateUserProfileCommand);

    //delete user
    void deleteUser(DeleteUserCommand deleteUserCommand);
}
