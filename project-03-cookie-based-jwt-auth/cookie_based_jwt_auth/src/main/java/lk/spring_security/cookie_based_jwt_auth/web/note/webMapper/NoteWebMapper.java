package lk.spring_security.cookie_based_jwt_auth.web.note.webMapper;

import lk.spring_security.cookie_based_jwt_auth.domain.models.Note;
import lk.spring_security.cookie_based_jwt_auth.web.note.DTOs.NoteRequestDTO;
import lk.spring_security.cookie_based_jwt_auth.web.note.DTOs.NoteResponseDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface NoteWebMapper {
    //domain model to response
    NoteResponseDTO toResponseDTO(Note note);

    //requestDTO to domain model
    Note toDomainModel(NoteRequestDTO noteRequestDTO);
}
