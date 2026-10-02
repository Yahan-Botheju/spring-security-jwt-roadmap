package lk.spring_security.method_level_security_global_security_exceptions.web.user.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserResponseDTO {
    private Long userId;
    private String email;
    private String role;
}
