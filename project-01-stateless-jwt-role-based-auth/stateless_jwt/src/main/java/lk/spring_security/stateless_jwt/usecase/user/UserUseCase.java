package lk.spring_security.stateless_jwt.usecase.user;

import lk.spring_security.stateless_jwt.domain.models.User;
import lk.spring_security.stateless_jwt.usecase.user.records.GetUserProfileCommand;
import lk.spring_security.stateless_jwt.usecase.user.records.GetUserProfileResult;

public interface UserUseCase {

    //user profile
    User getUserProfile(String email);

    //update user profile
    User updateUser(User user, String currentEmail);

    //delete user
    void deleteUser(String email);

    //get user profile
    GetUserProfileResult userProfile(GetUserProfileCommand getUserProfileCommand);
}
