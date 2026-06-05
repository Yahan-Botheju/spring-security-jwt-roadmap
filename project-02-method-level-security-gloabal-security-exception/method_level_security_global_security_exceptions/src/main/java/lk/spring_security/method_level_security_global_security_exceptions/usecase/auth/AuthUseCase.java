package lk.spring_security.method_level_security_global_security_exceptions.usecase.auth;

import lk.spring_security.method_level_security_global_security_exceptions.domain.models.User;

public interface AuthUseCase {
    //initiate register user
    String registerUser(User user);

    //initiate login user
    String loginUser(String email, String password);
}
