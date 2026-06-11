package lk.spring_security.cookie_based_jwt_auth.domain.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Long userId;
    private String email;
    private String password;
    private Role role;
}
