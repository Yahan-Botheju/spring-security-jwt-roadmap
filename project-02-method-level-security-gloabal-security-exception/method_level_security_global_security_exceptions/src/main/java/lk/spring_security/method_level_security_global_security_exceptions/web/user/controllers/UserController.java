package lk.spring_security.method_level_security_global_security_exceptions.web.user.controllers;

import lk.spring_security.method_level_security_global_security_exceptions.usecase.user.UserUseCase;
import lk.spring_security.method_level_security_global_security_exceptions.web.user.webMappers.UserWebMapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v2/users")
public class UserController {

    //inject required classes
    private final UserUseCase userUseCase;
    private final UserWebMapper  userWebMapper;

    public UserController(UserUseCase userUseCase, UserWebMapper userWebMapper) {
        this.userUseCase = userUseCase;
        this.userWebMapper = userWebMapper;
    }
}
