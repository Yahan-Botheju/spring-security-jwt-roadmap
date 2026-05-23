package lk.spring_security.stateless_jwt.web.user.Controller;

import lk.spring_security.stateless_jwt.usecase.user.UserUseCase;
import lk.spring_security.stateless_jwt.web.user.webMappers.UserWebMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    //inject user usecase
    private final UserUseCase userUseCase;

    //inject user web mapper
    private final UserWebMapper userWebMapper;
}
