package lk.spring_security.stateless_jwt.usecase.user;

import lk.spring_security.stateless_jwt.domain.models.User;
import lk.spring_security.stateless_jwt.domain.repositories.UserRepository;
import lk.spring_security.stateless_jwt.usecase.user.records.*;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;

public class UserUseCaseImpl implements  UserUseCase {

    //inject user domain repo
    private final UserRepository userRepository;

    public UserUseCaseImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //get user profile
    @Override
    public GetUserProfileResult userProfile(GetUserProfileCommand getUserProfileCommand) {
        //check incoming fields
        if(getUserProfileCommand.email().isEmpty()){
            throw new IllegalStateException("User email is required");
        }
        //get user
        User user = userRepository.userFindByEmail(getUserProfileCommand.email())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));


        return new GetUserProfileResult(
                user.getUserId(),
                user.getEmail(),
                user.getRole().toString()
        );
    }

    //update user profile
    @Override
    public UpdateUserProfileResult updateProfile(UpdateUserProfileCommand updateUserProfileCommand) {
        //check incoming fields
        if(updateUserProfileCommand.currentEmail().isEmpty() || updateUserProfileCommand.newEmail().isEmpty()){
            throw new IllegalStateException("User email is required");
        }
        //get user
        User checkUser = userRepository.userFindByEmail(updateUserProfileCommand.currentEmail())
                .orElseThrow(() ->  new IllegalArgumentException("User not found"));
        //update email using domain logic
        checkUser.updateEmail(updateUserProfileCommand.newEmail());

        User updateUser = userRepository.updateUser(checkUser);

        return new  UpdateUserProfileResult(
                updateUser.getUserId(),
                updateUser.getEmail(),
                updateUser.getRole().toString()
        );
    }

    //delete user
    @Override
    public void deleteUser(DeleteUserCommand deleteUserCommand) {
        //check incoming fields
        if(deleteUserCommand.email().isEmpty()){
            throw new IllegalStateException("User email is required");
        }
        User existingUser = userRepository.userFindByEmail(deleteUserCommand.email())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        userRepository.deleteUser(existingUser);
    }



}


























