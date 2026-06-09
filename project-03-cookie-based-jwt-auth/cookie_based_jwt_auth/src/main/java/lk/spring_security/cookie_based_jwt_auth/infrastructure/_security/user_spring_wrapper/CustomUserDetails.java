package lk.spring_security.cookie_based_jwt_auth.infrastructure._security.user_spring_wrapper;

import lk.spring_security.cookie_based_jwt_auth.domain.models.User;
import org.springframework.security.core.userdetails.UserDetails;

public class CustomUserDetails  implements UserDetails {

    //inject user domain model
    private final User user;

    public CustomUserDetails(User user) {
        this.user = user;
    }
}
