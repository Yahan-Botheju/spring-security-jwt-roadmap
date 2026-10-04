package lk.spring_security.stateless_jwt.usecase.user.records;

public record UpdateUserProfileResult(
        Long userId,
        String email,
        String role
) {
}
