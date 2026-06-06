package lk.spring_security.cookie_based_jwt_auth.infrastructure.note.persistence.jpa;

import lk.spring_security.cookie_based_jwt_auth.infrastructure.note.persistence.entities.NoteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaNoteRepository extends JpaRepository<NoteEntity, Long> {
}
