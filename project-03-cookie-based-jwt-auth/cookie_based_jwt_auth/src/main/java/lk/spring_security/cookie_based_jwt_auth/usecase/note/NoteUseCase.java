package lk.spring_security.cookie_based_jwt_auth.usecase.note;

import lk.spring_security.cookie_based_jwt_auth.domain.models.Note;
import lk.spring_security.cookie_based_jwt_auth.usecase.note.records.CreateNoteCommand;
import lk.spring_security.cookie_based_jwt_auth.usecase.note.records.CreateNoteResult;
import lk.spring_security.cookie_based_jwt_auth.usecase.note.records.GetAllNotesResult;

import java.util.List;

public interface NoteUseCase {

    //get user all notes
    List<GetAllNotesResult> getAllNotesByUserId(Long userId);

    //create note
    CreateNoteResult createNote(CreateNoteCommand createNoteCommand);

    //update note
    Note updateNote(Long userId ,Long noteId, Note note);

    //delete note
    void deleteNote(Long noteId, Long userId);

    //testing update note
    Note testingUpdateNote(Long userId ,Long noteId, Note note);
}
