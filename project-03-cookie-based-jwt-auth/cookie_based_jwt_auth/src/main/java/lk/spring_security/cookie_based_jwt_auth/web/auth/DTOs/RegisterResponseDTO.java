package lk.spring_security.cookie_based_jwt_auth.web.auth.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterResponseDTO {
    private Long userId;
    private String email;
    private String role;
    private String token;
}
