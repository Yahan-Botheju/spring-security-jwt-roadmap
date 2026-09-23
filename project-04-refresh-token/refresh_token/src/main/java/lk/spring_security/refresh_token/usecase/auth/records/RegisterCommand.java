package lk.spring_security.refresh_token.usecase.auth.records;

public record RegisterCommand(
         String email,
         String password
) {
}
