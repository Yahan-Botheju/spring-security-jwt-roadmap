package lk.spring_security.refresh_token.domain.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Long userId;
    private String email;
    private String password;
    private Role role;
}
