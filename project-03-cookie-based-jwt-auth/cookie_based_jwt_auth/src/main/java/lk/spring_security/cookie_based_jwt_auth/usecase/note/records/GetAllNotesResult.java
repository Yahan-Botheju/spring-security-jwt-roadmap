package lk.spring_security.cookie_based_jwt_auth.usecase.note.records;

public record GetAllNotesResult(
        Long noteId,
        String title,
        String content,
        Long userId
) {
}
