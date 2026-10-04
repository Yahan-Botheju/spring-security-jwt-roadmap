package lk.spring_security.stateless_jwt.usecase.user.records;

public record UpdateUserProfileCommand(
        String currentEmail,
        String newEmail
) {
}
