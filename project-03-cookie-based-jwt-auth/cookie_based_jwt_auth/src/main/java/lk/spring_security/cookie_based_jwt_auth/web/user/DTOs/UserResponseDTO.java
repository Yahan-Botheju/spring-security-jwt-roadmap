package lk.spring_security.cookie_based_jwt_auth.web.user.DTOs;

import lk.spring_security.cookie_based_jwt_auth.domain.models.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDTO {
    private Long userId;
    private String email;

    private Role role;
}
