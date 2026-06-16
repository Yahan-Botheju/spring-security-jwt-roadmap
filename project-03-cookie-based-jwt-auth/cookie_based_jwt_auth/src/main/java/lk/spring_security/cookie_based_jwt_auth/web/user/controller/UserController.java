package lk.spring_security.cookie_based_jwt_auth.web.user.controller;

import lk.spring_security.cookie_based_jwt_auth.usecase.user.UserUseCase;
import lk.spring_security.cookie_based_jwt_auth.web.user.webMapper.UserWebMapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    //inject required class
    private final UserUseCase userUseCase;
    private final UserWebMapper userWebMapper;

    public UserController(
            UserUseCase userUseCase,
            UserWebMapper userWebMapper
    ) {
        this.userUseCase = userUseCase;
        this.userWebMapper = userWebMapper;
    }
}
