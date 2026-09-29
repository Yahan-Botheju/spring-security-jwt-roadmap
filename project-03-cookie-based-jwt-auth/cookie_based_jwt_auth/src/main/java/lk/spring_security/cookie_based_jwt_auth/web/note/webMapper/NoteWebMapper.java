package lk.spring_security.cookie_based_jwt_auth.web.note.webMapper;

import lk.spring_security.cookie_based_jwt_auth.domain.models.Note;
import lk.spring_security.cookie_based_jwt_auth.usecase.note.records.CreateNoteCommand;
import lk.spring_security.cookie_based_jwt_auth.usecase.note.records.CreateNoteResult;
import lk.spring_security.cookie_based_jwt_auth.usecase.note.records.GetAllNotesResult;
import lk.spring_security.cookie_based_jwt_auth.web.note.DTOs.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface NoteWebMapper {
    //domain model to response
    @Mapping(source = "user.userId", target = "userId")
    NoteResponseDTO toResponseDTO(Note note);

    //requestDTO to domain model
    Note toDomainModel(NoteRequestDTO noteRequestDTO);

    /* __GET_ALL_NOTES__ */

    //domain mode to responseDTO
    GetAllNotesResponseDTO toGetAllNotesResponseDTO(GetAllNotesResult noteResult);


    /* __CREATE_NOTE__ */

    //requestDTO to command
    CreateNoteCommand toCreateNoteCommand(Long userId ,CreateNoteRequestDTO createNoteRequestDTO);

    //domain model to usecase
    CreateNoteResponseDTO  toCreateNoteResponseDTO(CreateNoteResult createNoteResult);
}
