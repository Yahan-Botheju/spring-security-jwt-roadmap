package lk.spring_security.cookie_based_jwt_auth.domain.repositories;

import lk.spring_security.cookie_based_jwt_auth.domain.models.Note;

import java.util.List;

public interface NoteRepository {

    //get user all notes
    List<Note> getAllNotesByUserId(Long userId);

    //create note
    Note createNote(Note note);

}
