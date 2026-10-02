package lk.spring_security.method_level_security_global_security_exceptions.usecase.user;

import lk.spring_security.method_level_security_global_security_exceptions.domain.models.User;
import lk.spring_security.method_level_security_global_security_exceptions.domain.repositories.UserRepository;
import lk.spring_security.method_level_security_global_security_exceptions.usecase.user.records.UpdateUserCommand;
import lk.spring_security.method_level_security_global_security_exceptions.usecase.user.records.UpdateUserResult;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


public class UserUseCaseImpl implements UserUseCase {

    //inject user repo
    private final UserRepository userRepository;

    //create constructor
    public UserUseCaseImpl(
            UserRepository userRepository
    ) {
        this.userRepository = userRepository;
    }

    //update user
    @Override
    public UpdateUserResult updateUser(UpdateUserCommand updateUserCommand) {
        //validate incoming fields
        if (updateUserCommand.email().isBlank()
                || updateUserCommand.userId() == null
        ) {
            throw new IllegalStateException("Required fields cannot be empty!!");
        }

        User exsitingUser = userRepository.findById(updateUserCommand.userId())
                .orElseThrow(() -> new UsernameNotFoundException("user not found" + " , " + updateUserCommand.userId()));

        //update user email and password through domain
        exsitingUser.updateUserEmail(updateUserCommand.email(),  updateUserCommand.userId());

        User updatedUser = userRepository.updateUser(exsitingUser);

        return new UpdateUserResult(
                updatedUser.getUserId(),
                updateUserCommand.email(),
                exsitingUser.getRole().toString()
        );
    }


    //delete user
    @Override
    public void deleteUser(Long userId) {
        User existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new UsernameNotFoundException("user not found" + " , " + userId));
        userRepository.deleteUser(existingUser);
    }
}





























