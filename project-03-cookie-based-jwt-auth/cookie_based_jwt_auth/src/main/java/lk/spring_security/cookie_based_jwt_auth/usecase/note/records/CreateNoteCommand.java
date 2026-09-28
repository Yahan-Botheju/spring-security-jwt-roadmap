package lk.spring_security.cookie_based_jwt_auth.usecase.note.records;

public record CreateNoteCommand(
        String title,
        String content
) {
}
