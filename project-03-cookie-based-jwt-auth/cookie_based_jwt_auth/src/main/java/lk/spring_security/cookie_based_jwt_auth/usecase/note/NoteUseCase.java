package lk.spring_security.cookie_based_jwt_auth.usecase.note;

import lk.spring_security.cookie_based_jwt_auth.domain.models.Note;

import java.util.List;

public interface NoteUseCase {

    //get user all notes
    List<Note> getAllNotesByUserId(Long userId);

    //create note
    Note createNote(Long userId ,Note note);

    //update note
    Note updateNote(Long userId ,Long noteId, Note note);

    //delete note
    void deleteNote(Long noteId, Long userId);

    //testing update note
    Note testingUpdateNote(Long userId ,Long noteId, Note note);
}
