package lk.spring_security.cookie_based_jwt_auth.usecase.auth.records;

public record RegisterCommand(
        String email,
        String password
) {
}
