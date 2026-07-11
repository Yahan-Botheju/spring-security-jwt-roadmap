package lk.spring_security.stateful_jwt_refresh_token_rotation.infrastructure._security.user_spring_wrapper;

import lk.spring_security.stateful_jwt_refresh_token_rotation.domain.models.User;
import org.springframework.security.core.userdetails.UserDetails;

public class CustomUserDetails implements UserDetails {

    //inject user domain model
    private final User user;

    public CustomUserDetails(User user) {
        this.user=user;
    }
}
