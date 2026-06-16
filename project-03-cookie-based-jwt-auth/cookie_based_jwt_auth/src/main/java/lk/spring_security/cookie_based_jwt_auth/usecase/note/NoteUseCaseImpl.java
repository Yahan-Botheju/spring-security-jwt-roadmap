package lk.spring_security.cookie_based_jwt_auth.usecase.note;

import lk.spring_security.cookie_based_jwt_auth.domain.models.Note;
import lk.spring_security.cookie_based_jwt_auth.domain.models.User;
import lk.spring_security.cookie_based_jwt_auth.domain.repositories.NoteRepository;
import lk.spring_security.cookie_based_jwt_auth.domain.repositories.UserRepository;

import java.util.List;

public class NoteUseCaseImpl implements  NoteUseCase {

    //inject required classes
    private final NoteRepository noteRepository;
    private final UserRepository userRepository;


    public NoteUseCaseImpl(NoteRepository noteRepository, UserRepository userRepository) {
        this.noteRepository = noteRepository;
        this.userRepository = userRepository;
    }

    //get user all notes
    @Override
    public List<Note> getAllNotesByUserId(Long userId){
        return noteRepository.getAllNotesByUserId(userId);
    }

    //create note
    @Override
    public Note createNote(Long userId, Note note){
        //check user availability
        User existingUser = userRepository.userFindById(userId)
                .orElseThrow(() -> new RuntimeException("User not found" +  " , " +  userId));

        //set note to user
        note.setUser(existingUser);

        return noteRepository.createNote(note);
    }

    //update note
    @Override
    public Note updateNote(Long userId, Long noteId, Note note){
        //check user availability
        if (!userRepository.userFindById(userId).isPresent()) {
            throw new RuntimeException("User not found" +  " , " +  userId);
        }
        //check note availability
        Note existingNote = noteRepository.findById(noteId)
                .orElseThrow(() -> new RuntimeException("Note not found" +  " , " +  noteId));

        //check note belongs to user
        if (!existingNote.getUser().getUserId().equals(userId)) {
            throw new RuntimeException("User not found" +  " , " +  userId);
        }

        return noteRepository.updateNote(noteId, note);
    }

    //delete note
    @Override
    public void deleteNote(Long userId, Long noteId){
        if(!userRepository.userFindById(userId).isPresent()){
            throw  new RuntimeException("User not found" +  " , " +  userId);
        }
        noteRepository.deleteNote(noteId);
    }


    //testing update note
    @Override
    public Note testingUpdateNote(Long userId, Long noteId, Note note){
        if(!userRepository.userFindById(userId).isPresent()){
            throw  new RuntimeException("User not found" +  " , " +  userId);
        }
        Note existingNote = noteRepository.findById(noteId)
                .orElseThrow(() -> new RuntimeException("Note not found" +  " , " +  noteId));
        if(!existingNote.getUser().getUserId().equals(userId)){
            throw  new RuntimeException("user does not belongs to this note" +  " , " +  userId);
        }

        return noteRepository.testUpdateNote(note, existingNote);
    }
}
