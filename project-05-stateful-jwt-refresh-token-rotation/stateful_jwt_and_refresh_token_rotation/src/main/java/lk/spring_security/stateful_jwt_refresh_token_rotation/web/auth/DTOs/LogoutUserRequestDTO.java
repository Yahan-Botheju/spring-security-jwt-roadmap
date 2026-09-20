package lk.spring_security.stateful_jwt_refresh_token_rotation.web.auth.DTOs;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LogoutUserRequestDTO {

    @NotBlank(message = "Refresh token cannot be empty")
    private String refreshToken;
}
