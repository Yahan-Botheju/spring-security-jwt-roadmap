package lk.spring_security.stateful_jwt_refresh_token_rotation.domain.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    private Long userId;
    private String email;
    private String password;

    private Role role;

    /* __DOMAIN_LOGIC__ */
    public void setDefaultRole(){
        if(role != null){
            throw new IllegalStateException("There is already a role set");
        }
        this.role = Role.USER;
    }
}
