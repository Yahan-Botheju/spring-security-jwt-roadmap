package lk.spring_security.cookie_based_jwt_auth.usecase.note;

import lk.spring_security.cookie_based_jwt_auth.domain.repositories.NoteRepository;

public class NoteUseCaseImpl implements  NoteUseCase {

    //inject required classes
    private final NoteRepository noteRepository;

    public NoteUseCaseImpl(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }
}
