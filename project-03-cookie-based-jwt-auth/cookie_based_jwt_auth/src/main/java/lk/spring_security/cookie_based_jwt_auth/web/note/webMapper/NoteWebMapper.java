package lk.spring_security.cookie_based_jwt_auth.web.note.webMapper;

import lk.spring_security.cookie_based_jwt_auth.usecase.note.records.*;
import lk.spring_security.cookie_based_jwt_auth.web.note.DTOs.*;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface NoteWebMapper {

    /* __GET_ALL_NOTES__ */

    //domain mode to responseDTO
    GetAllNotesResponseDTO toGetAllNotesResponseDTO(GetAllNotesResult noteResult);


    /* __CREATE_NOTE__ */

    //requestDTO to command
    CreateNoteCommand toCreateNoteCommand(Long userId ,CreateNoteRequestDTO createNoteRequestDTO);

    //domain model to usecase
    CreateNoteResponseDTO  toCreateNoteResponseDTO(CreateNoteResult createNoteResult);


    /* __UPDATE_NOTE__ */

    //requestDTO to command
    UpdateNoteCommand  toUpdateNoteCommand(Long userId ,Long noteId, UpdateNoteRequestDTO updateNoteRequestDTO);

    //domain model to responseDTO
    UpdateNoteResponseDTO toUpdateNoteResponseDTO(UpdateNoteResult updateNoteResult);
}
