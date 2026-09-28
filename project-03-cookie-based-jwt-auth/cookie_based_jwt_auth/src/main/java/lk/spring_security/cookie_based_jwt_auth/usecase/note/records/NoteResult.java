package lk.spring_security.cookie_based_jwt_auth.usecase.note.records;

public record NoteResult(
        Long noteId,
        String title,
        String content,
        Long userId
) {
}
