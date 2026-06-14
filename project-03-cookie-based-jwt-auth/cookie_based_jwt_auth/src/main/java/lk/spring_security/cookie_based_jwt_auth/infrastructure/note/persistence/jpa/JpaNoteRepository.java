package lk.spring_security.cookie_based_jwt_auth.infrastructure.note.persistence.jpa;

import lk.spring_security.cookie_based_jwt_auth.infrastructure.note.persistence.entities.NoteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface JpaNoteRepository extends JpaRepository<NoteEntity, Long> {

    List<NoteEntity> findByUser_UserId(@Param("userId") Long userId);
}
