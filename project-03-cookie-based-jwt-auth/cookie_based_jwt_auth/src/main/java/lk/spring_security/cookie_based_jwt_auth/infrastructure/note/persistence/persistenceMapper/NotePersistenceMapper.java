package lk.spring_security.cookie_based_jwt_auth.infrastructure.note.persistence.persistenceMapper;

import lk.spring_security.cookie_based_jwt_auth.domain.models.Note;
import lk.spring_security.cookie_based_jwt_auth.infrastructure.note.persistence.entities.NoteEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface NotePersistenceMapper {
    //domain model to entity
    NoteEntity toEntity(Note note);

    //entity to domain model
    Note toDomainModel(NoteEntity noteEntity);
}
