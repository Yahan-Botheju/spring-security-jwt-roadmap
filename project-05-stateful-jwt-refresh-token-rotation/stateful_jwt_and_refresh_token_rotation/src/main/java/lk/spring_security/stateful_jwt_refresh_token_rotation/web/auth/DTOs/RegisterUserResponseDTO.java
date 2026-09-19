package lk.spring_security.stateful_jwt_refresh_token_rotation.web.auth.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterUserResponseDTO {
    private Long userId;
    private String email;
    private String userRole;
    private Long walletId;
    private Double walletBalance;
}
