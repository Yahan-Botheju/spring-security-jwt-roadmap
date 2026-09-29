package lk.spring_security.cookie_based_jwt_auth.usecase.auth;

import lk.spring_security.cookie_based_jwt_auth.domain.models.User;
import lk.spring_security.cookie_based_jwt_auth.domain.repositories.IdentityManager;
import lk.spring_security.cookie_based_jwt_auth.domain.repositories.UserRepository;
import lk.spring_security.cookie_based_jwt_auth.domain.repositories.CookieService;
import lk.spring_security.cookie_based_jwt_auth.usecase.auth.records.LoginCommand;
import lk.spring_security.cookie_based_jwt_auth.usecase.auth.records.LoginResult;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class LoginUseCaseImpl implements LoginUseCase {

    //inject required dependencies
    private final UserRepository userRepository;
    private final CookieService cookieService;
    private final IdentityManager identityManager;

    public LoginUseCaseImpl(
            UserRepository userRepository,
            CookieService cookieService,
            IdentityManager identityManager
    ) {
        this.userRepository = userRepository;
        this.cookieService = cookieService;
        this.identityManager = identityManager;
    }

    //login user
    @Override
    public LoginResult login(LoginCommand loginCommand) {

        if(loginCommand.email() ==  null || loginCommand.password() == null){
            throw new ResourceNotFoundException("Email or Password cannot be empty!!");
        }
        //authenticate give username and password
        identityManager.authenticate(loginCommand.email(), loginCommand.password());

        User user = userRepository.userFindByEmail(loginCommand.email())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        String token = cookieService.generateToken(user);

        return new LoginResult(
                token,
                user.getEmail(),
                "User logged in successfully!"
        );
    }
}
