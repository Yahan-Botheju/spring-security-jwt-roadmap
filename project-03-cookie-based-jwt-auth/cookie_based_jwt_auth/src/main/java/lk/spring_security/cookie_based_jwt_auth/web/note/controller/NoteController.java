package lk.spring_security.cookie_based_jwt_auth.web.note.controller;

import jakarta.validation.Valid;
import lk.spring_security.cookie_based_jwt_auth.domain.models.Note;
import lk.spring_security.cookie_based_jwt_auth.infrastructure._security.user_spring_wrapper.CustomUserDetails;
import lk.spring_security.cookie_based_jwt_auth.usecase.note.NoteUseCase;
import lk.spring_security.cookie_based_jwt_auth.web.note.DTOs.NoteRequestDTO;
import lk.spring_security.cookie_based_jwt_auth.web.note.DTOs.NoteResponseDTO;
import lk.spring_security.cookie_based_jwt_auth.web.note.webMapper.NoteWebMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/notes")
public class NoteController {

    //inject required classes
    private final NoteUseCase noteUseCase;
    private final NoteWebMapper noteWebMapper;

    public NoteController(
            NoteUseCase noteUseCase,
            NoteWebMapper noteWebMapper
    ) {
        this.noteUseCase = noteUseCase;
        this.noteWebMapper = noteWebMapper;
    }

    //get notes related to user
    @GetMapping
    public ResponseEntity<List<NoteResponseDTO>> getAllNotes(
            @AuthenticationPrincipal CustomUserDetails  customUserDetails
            ){

        List<Note> getAllNotes = noteUseCase.getAllNotesByUserId(customUserDetails.getUserId());
        List<NoteResponseDTO> responseDTOS = getAllNotes.stream()
                .map(noteWebMapper::toResponseDTO).collect(Collectors.toList());

        return ResponseEntity.ok(responseDTOS);
    }

    //create new note
    @PostMapping
    public ResponseEntity<NoteResponseDTO> createNote(
            @AuthenticationPrincipal CustomUserDetails  customUserDetails,
            @Valid @RequestBody NoteRequestDTO noteRequestDTO
            ){
        Long getUserId = customUserDetails.getUserId();
        Note toDomainModel = noteWebMapper.toDomainModel(noteRequestDTO);

        NoteResponseDTO responseDTO = noteWebMapper.toResponseDTO(noteUseCase.createNote(getUserId, toDomainModel));

        return ResponseEntity.created(URI.create("/api/v1/notes")).body(responseDTO);
    }
}
