package lk.spring_security.cookie_based_jwt_auth.domain.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Note {
    private Long noteId;
    private String title;
    private String content;

    private User user;
}
