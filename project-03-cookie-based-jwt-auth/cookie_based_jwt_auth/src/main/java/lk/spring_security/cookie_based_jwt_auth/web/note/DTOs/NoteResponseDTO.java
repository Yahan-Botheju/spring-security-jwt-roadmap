package lk.spring_security.cookie_based_jwt_auth.web.note.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NoteResponseDTO {
    private Long noteId;
    private String title;
    private String content;

    private Long userId;
}
