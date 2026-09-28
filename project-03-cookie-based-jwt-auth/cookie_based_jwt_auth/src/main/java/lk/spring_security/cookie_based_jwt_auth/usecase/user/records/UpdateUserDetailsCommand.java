package lk.spring_security.cookie_based_jwt_auth.usecase.user.records;

public record UpdateUserDetailsCommand(
        Long userId,
        String email,
        String password
) {
}
