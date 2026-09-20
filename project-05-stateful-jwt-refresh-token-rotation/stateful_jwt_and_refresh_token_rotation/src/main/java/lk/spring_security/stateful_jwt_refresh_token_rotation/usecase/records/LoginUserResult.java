package lk.spring_security.stateful_jwt_refresh_token_rotation.usecase.records;

import lk.spring_security.stateful_jwt_refresh_token_rotation.domain.models.Role;

public record LoginUserResult(
        String accessToken,
        String refreshToken,
        Long userId,
        String email,
        String password,
        Role role
) {
}
