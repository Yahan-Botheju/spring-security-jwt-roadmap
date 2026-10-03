package lk.spring_security.stateless_jwt.usecase.auth.records;

public record LoginResult(
        String email,
        String token
) {
}
