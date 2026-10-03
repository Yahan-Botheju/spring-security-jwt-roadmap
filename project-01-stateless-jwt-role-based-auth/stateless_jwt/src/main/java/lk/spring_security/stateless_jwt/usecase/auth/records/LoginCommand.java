package lk.spring_security.stateless_jwt.usecase.auth.records;

public record LoginCommand(
        String email,
        String password
) {
}
