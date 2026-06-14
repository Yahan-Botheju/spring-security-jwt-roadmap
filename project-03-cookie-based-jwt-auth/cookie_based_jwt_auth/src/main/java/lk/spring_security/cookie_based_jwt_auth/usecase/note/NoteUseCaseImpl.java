package lk.spring_security.cookie_based_jwt_auth.usecase.note;

import lk.spring_security.cookie_based_jwt_auth.domain.models.Note;
import lk.spring_security.cookie_based_jwt_auth.domain.repositories.NoteRepository;

import java.util.List;

public class NoteUseCaseImpl implements  NoteUseCase {

    //inject required classes
    private final NoteRepository noteRepository;

    public NoteUseCaseImpl(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    //get user all notes
    @Override
    public List<Note> getAllNotesByUserId(Long userId){
        return noteRepository.getAllNotesByUserId(userId);
    }
}
