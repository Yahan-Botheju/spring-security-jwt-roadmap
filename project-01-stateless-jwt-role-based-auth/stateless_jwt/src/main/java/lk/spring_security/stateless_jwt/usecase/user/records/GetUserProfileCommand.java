package lk.spring_security.stateless_jwt.usecase.user.records;

public record GetUserProfileCommand(
        String email
) {
}
