package lk.spring_security.refresh_token.usecase.auth.records;

import java.time.Instant;

public record LoginResult(
        Long userId,
        String email,
        String role,
        String refreshToken,
        String accessToken,
        Instant expiryDate
) {
}
