package lk.spring_security.refresh_token.usecase.auth.records;

import lk.spring_security.refresh_token.domain.enums.Role;

import java.time.LocalDateTime;

public record LoginResult(
        Long userId,
        String email,
        Role role,
        String refreshToken,
        String accessToken,
        LocalDateTime expiryDate
) {
}
