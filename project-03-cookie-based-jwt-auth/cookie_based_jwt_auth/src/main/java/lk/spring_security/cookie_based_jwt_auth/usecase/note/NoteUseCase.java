package lk.spring_security.cookie_based_jwt_auth.usecase.note;

import lk.spring_security.cookie_based_jwt_auth.domain.models.Note;

import java.util.List;

public interface NoteUseCase {

    //get user all notes
    List<Note> getAllNotesByUserId(Long userId);
}
