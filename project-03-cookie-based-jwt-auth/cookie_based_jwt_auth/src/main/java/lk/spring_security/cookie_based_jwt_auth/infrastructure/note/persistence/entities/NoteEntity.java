package lk.spring_security.cookie_based_jwt_auth.infrastructure.note.persistence.entities;

import jakarta.persistence.*;
import lk.spring_security.cookie_based_jwt_auth.infrastructure.user.persistence.entities.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SoftDelete;


@Entity
@Table(name = "notes")
@SoftDelete
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NoteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long noteId;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "Text", nullable = false)
    private String content;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;
}
