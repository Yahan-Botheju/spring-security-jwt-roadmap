package lk.spring_security.method_level_security_global_security_exceptions.usecase.auth.records;

public record LoginResult(
        Long userId,
        String email,
        String token,
        String role
) {
}
