package lk.spring_security.cookie_based_jwt_auth.usecase.user.records;


public record UpdateUserDetailsResult(
        Long userId,
        String email,
        String role
) {
}
