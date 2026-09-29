package lk.spring_security.cookie_based_jwt_auth.usecase.note.records;

public record CreateNoteResult(
        Long userId,
        String email,
        String role,
        Long noteId,
        String title,
        String content
) {
}
