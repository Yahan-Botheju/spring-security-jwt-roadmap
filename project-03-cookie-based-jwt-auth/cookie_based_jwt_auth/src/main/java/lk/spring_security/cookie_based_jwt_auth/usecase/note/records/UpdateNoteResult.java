package lk.spring_security.cookie_based_jwt_auth.usecase.note.records;

public record UpdateNoteResult(
        Long userId,
        String email,
        Long noteId,
        String title,
        String content
) {
}
