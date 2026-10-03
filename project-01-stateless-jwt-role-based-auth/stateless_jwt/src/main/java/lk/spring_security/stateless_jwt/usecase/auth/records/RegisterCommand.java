package lk.spring_security.stateless_jwt.usecase.auth.records;

public record RegisterCommand(
        String email,
        String password
) {
}
