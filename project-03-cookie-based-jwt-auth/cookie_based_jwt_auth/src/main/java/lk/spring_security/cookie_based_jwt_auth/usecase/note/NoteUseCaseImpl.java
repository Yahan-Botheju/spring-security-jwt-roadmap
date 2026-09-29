package lk.spring_security.cookie_based_jwt_auth.usecase.note;

import lk.spring_security.cookie_based_jwt_auth.domain.models.Note;
import lk.spring_security.cookie_based_jwt_auth.domain.models.User;
import lk.spring_security.cookie_based_jwt_auth.domain.repositories.NoteRepository;
import lk.spring_security.cookie_based_jwt_auth.domain.repositories.UserRepository;
import lk.spring_security.cookie_based_jwt_auth.usecase.note.records.CreateNoteCommand;
import lk.spring_security.cookie_based_jwt_auth.usecase.note.records.CreateNoteResult;
import lk.spring_security.cookie_based_jwt_auth.usecase.note.records.GetAllNotesResult;

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
    public List<GetAllNotesResult> getAllNotesByUserId(Long userId){
        return noteRepository.getAllNotesByUserId(userId).stream()
                .map(note -> new GetAllNotesResult(
                        note.getNoteId(),
                        note.getTitle(),
                        note.getContent(),
                        note.getUser().getUserId()))
                .toList();
    }
    //create note
    @Override
    public CreateNoteResult createNote(CreateNoteCommand createNoteCommand) {
        //check user existence
        User user = userRepository.userFindById(createNoteCommand.userId())
                .orElseThrow(() -> new RuntimeException("user not found"));
        //create note model
        Note newNote = Note.createNewNote(
                createNoteCommand.title(),
                createNoteCommand.content(),
                user
        );
        //save note
        Note savedNote = noteRepository.createNote(newNote);

        return new  CreateNoteResult(
                user.getUserId(),
                user.getEmail(),
                user.getRole().toString(),
                savedNote.getNoteId(),
                savedNote.getTitle(),
                savedNote.getContent()
        );
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
