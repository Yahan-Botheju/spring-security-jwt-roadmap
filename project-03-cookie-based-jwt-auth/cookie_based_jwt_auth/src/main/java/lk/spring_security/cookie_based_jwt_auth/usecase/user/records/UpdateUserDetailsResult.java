package lk.spring_security.cookie_based_jwt_auth.usecase.user.records;

import lk.spring_security.cookie_based_jwt_auth.domain.models.Role;

public record UpdateUserDetailsResult(
        Long userId,
        String email,
        Role role
) {
}
