package lk.spring_security.stateless_jwt.usecase.user.records;

import lk.spring_security.stateless_jwt.domain.models.Role;

public record GetUserProfileResult(
        Long userId,
        String email,
        String role
) {
}
