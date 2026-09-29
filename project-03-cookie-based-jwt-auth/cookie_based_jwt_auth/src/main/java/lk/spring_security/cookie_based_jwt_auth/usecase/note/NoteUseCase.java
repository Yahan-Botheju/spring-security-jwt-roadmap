package lk.spring_security.cookie_based_jwt_auth.usecase.note;

import lk.spring_security.cookie_based_jwt_auth.usecase.note.records.*;

import java.util.List;

public interface NoteUseCase {

    //get user all notes
    List<GetAllNotesResult> getAllNotesByUserId(Long userId);

    //create note
    CreateNoteResult createNote(CreateNoteCommand createNoteCommand);

    //update note
    UpdateNoteResult updateNote(UpdateNoteCommand updateNoteCommand);

    //delete note
    void deleteNote(Long noteId, Long userId);
}
