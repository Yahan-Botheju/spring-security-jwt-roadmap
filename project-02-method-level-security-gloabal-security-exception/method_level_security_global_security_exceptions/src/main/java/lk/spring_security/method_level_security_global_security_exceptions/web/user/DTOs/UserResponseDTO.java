package lk.spring_security.method_level_security_global_security_exceptions.web.user.DTOs;

import lk.spring_security.method_level_security_global_security_exceptions.domain.models.Role;
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
