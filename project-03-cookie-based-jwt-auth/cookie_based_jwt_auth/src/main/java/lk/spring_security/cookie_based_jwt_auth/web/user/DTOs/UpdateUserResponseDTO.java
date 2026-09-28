package lk.spring_security.cookie_based_jwt_auth.web.user.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserResponseDTO {
    Long userId;
    String email;
    String role;
}
