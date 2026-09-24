package lk.spring_security.refresh_token.web.auth.DTOs;

import lk.spring_security.refresh_token.domain.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponseSTO {
    private Long userId;
    private String email;
    private Role role;
    private String refreshToken;
    private String accessToken;
    private LocalDateTime expiryDate;
}
