package lk.spring_security.cookie_based_jwt_auth.web.note.webMapper;

import lk.spring_security.cookie_based_jwt_auth.domain.models.Note;
import lk.spring_security.cookie_based_jwt_auth.usecase.note.records.CreateNoteCommand;
import lk.spring_security.cookie_based_jwt_auth.usecase.note.records.CreateNoteResult;
import lk.spring_security.cookie_based_jwt_auth.web.note.DTOs.CreateNoteRequestDTO;
import lk.spring_security.cookie_based_jwt_auth.web.note.DTOs.CreateNoteResponseDTO;
import lk.spring_security.cookie_based_jwt_auth.web.note.DTOs.NoteRequestDTO;
import lk.spring_security.cookie_based_jwt_auth.web.note.DTOs.NoteResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface NoteWebMapper {
    //domain model to response
    @Mapping(source = "user.userId", target = "userId")
    NoteResponseDTO toResponseDTO(Note note);

    //requestDTO to domain model
    Note toDomainModel(NoteRequestDTO noteRequestDTO);


    /* __CREATE_NOTE__ */

    //requestDTO to command
    CreateNoteCommand toCreateNoteCommand(CreateNoteRequestDTO createNoteRequestDTO);

    //domain model to usecase
    CreateNoteResponseDTO  toCreateNoteResponseDTO(CreateNoteResult createNoteResult);
}
