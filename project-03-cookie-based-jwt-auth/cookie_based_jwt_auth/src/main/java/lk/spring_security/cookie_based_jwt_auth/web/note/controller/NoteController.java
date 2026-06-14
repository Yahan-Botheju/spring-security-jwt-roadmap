package lk.spring_security.cookie_based_jwt_auth.web.note.controller;

import lk.spring_security.cookie_based_jwt_auth.usecase.note.NoteUseCase;
import lk.spring_security.cookie_based_jwt_auth.web.note.webMapper.NoteWebMapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
