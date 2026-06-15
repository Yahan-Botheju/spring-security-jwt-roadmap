package lk.spring_security.cookie_based_jwt_auth.domain.repositories;

import lk.spring_security.cookie_based_jwt_auth.domain.models.Note;

import java.util.List;
import java.util.Optional;

public interface NoteRepository {

    //get note by noteId
    Optional<Note> findById(Long noteId);

    //get user all notes
    List<Note> getAllNotesByUserId(Long userId);

    //create note
    Note createNote(Note note);

    //update note
    Note updateNote(Long noteId, Note note);

}
