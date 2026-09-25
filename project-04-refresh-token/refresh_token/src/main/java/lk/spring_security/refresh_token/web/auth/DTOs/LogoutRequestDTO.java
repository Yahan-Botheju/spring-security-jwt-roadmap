package lk.spring_security.refresh_token.web.auth.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LogoutRequestDTO {
    private String refreshToken;
}
