package lk.spring_security.method_level_security_global_security_exceptions.usecase.user;

import jakarta.transaction.Transactional;
import lk.spring_security.method_level_security_global_security_exceptions.domain.models.User;
import lk.spring_security.method_level_security_global_security_exceptions.domain.repositories.UserRepository;
import lk.spring_security.method_level_security_global_security_exceptions.infrastructure.user.persistence.userPersistenceMapper.UserPersistenceMapper;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserUseCaseImpl implements UserUseCase {

    //inject user repo
    private final UserRepository userRepository;

    //inject user persistence mapper
    private final UserPersistenceMapper userPersistenceMapper;

    //create constructor
    public UserUseCaseImpl(
            UserRepository userRepository,
            UserPersistenceMapper userPersistenceMapper
    ) {
        this.userRepository = userRepository;
        this.userPersistenceMapper = userPersistenceMapper;
    }

    //update user
    @Override
    @Transactional
    public User updateUser(String userEmail,  User user) {
        User exsitingUser = userRepository.userFindByEmail(userEmail)
                .orElseThrow(() -> new UsernameNotFoundException("user not found" + " , " +  userEmail));

        if(user.getEmail() != null || !user.getEmail().isEmpty()){
            exsitingUser.setEmail(user.getEmail());
        }
        return userRepository.updateUser(exsitingUser);
    }

    //delete user
    @Override
    public void deleteUser(String userEmail) {
        User existingUser = userRepository.userFindByEmail(userEmail)
                .orElseThrow(() -> new UsernameNotFoundException("user not found" + " , " +  userEmail));
        userRepository.deleteUser(existingUser);
    }
}
