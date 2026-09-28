package lk.spring_security.cookie_based_jwt_auth.usecase.user;

import lk.spring_security.cookie_based_jwt_auth.domain.models.User;
import lk.spring_security.cookie_based_jwt_auth.domain.repositories.UserRepository;
import lk.spring_security.cookie_based_jwt_auth.usecase.user.records.UpdateUserDetailsCommand;
import lk.spring_security.cookie_based_jwt_auth.usecase.user.records.UpdateUserDetailsResult;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;

public class UserUseCaseImpl implements UserUseCase{

    //inject required classes
    private final UserRepository userRepository;

    public UserUseCaseImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //update user
    @Override
    public UpdateUserDetailsResult updateUserDetails(UpdateUserDetailsCommand updateUserDetailsCommand) {
        //check incoming fields
        if(updateUserDetailsCommand.email().isEmpty() || updateUserDetailsCommand.password().isEmpty()){
            throw new IllegalArgumentException("Email or password cannot be empty");
        }
        //get user
        User currentUser = userRepository.userFindById(updateUserDetailsCommand.userId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        //update through domain model
        currentUser.updateUser(
                updateUserDetailsCommand.email(),
                updateUserDetailsCommand.password()
        );

        User savedUser = userRepository.updateUser(currentUser);

        return new UpdateUserDetailsResult(
                savedUser.getUserId(),
                savedUser.getEmail(),
                savedUser.getRole().toString()
        );
    }

    //delete user
    @Override
    public void deleteUser(Long userId) {
        User existingUser = userRepository.userFindById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        userRepository.deleteUser(existingUser.getUserId());
    }



}
