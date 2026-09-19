package lk.spring_security.stateful_jwt_refresh_token_rotation.usecase.records;

import lk.spring_security.stateful_jwt_refresh_token_rotation.domain.models.User;

import java.time.Instant;

public record RefreshTokenResult(
        Long tokenId,
        String token,
        Instant expiryDate,
        boolean isUsed,
        boolean isRevoked,
        User user
) {
}
