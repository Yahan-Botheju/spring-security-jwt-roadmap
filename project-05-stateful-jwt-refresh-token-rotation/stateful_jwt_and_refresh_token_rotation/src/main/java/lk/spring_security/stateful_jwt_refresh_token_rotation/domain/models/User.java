package lk.spring_security.stateful_jwt_refresh_token_rotation.domain.models;

import lombok.*;

@Getter
@Builder
public class User {
    private Long userId;
    private String email;
    private String password;

    private Role role;


    /* __FACTORY_METHOD__ */
    public static User createNewUser(
            Long userId,
            String email,
            String password,
            Role role
    ) {
        return new User(userId, email, password, role);
    }


    /* __DOMAIN_LOGIC__ */
    public void setDefaultRole() {
        if (role != null) {
            throw new IllegalStateException("There is already a role set");
        }
        this.role = Role.USER;
    }
}
