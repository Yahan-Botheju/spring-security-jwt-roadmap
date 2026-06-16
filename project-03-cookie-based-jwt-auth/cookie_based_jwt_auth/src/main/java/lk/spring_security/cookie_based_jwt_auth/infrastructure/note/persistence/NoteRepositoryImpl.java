package lk.spring_security.cookie_based_jwt_auth.infrastructure.note.persistence;

import jakarta.persistence.EntityNotFoundException;
import lk.spring_security.cookie_based_jwt_auth.domain.models.Note;
import lk.spring_security.cookie_based_jwt_auth.domain.repositories.NoteRepository;
import lk.spring_security.cookie_based_jwt_auth.infrastructure.note.persistence.entities.NoteEntity;
import lk.spring_security.cookie_based_jwt_auth.infrastructure.note.persistence.jpa.JpaNoteRepository;
import lk.spring_security.cookie_based_jwt_auth.infrastructure.note.persistence.persistenceMapper.NotePersistenceMapper;

import java.util.List;
import java.util.Optional;

public class NoteRepositoryImpl implements NoteRepository {

    //inject required classes
    private final JpaNoteRepository jpaNoteRepository;
    private final NotePersistenceMapper notePersistenceMapper;


    public NoteRepositoryImpl(
            JpaNoteRepository jpaNoteRepository,
            NotePersistenceMapper notePersistenceMapper
    ) {
        this.jpaNoteRepository = jpaNoteRepository;
        this.notePersistenceMapper = notePersistenceMapper;
    }

    //get note by noteId
    @Override
    public Optional<Note> findById(Long noteId){
        return jpaNoteRepository.findById(noteId)
                .map(notePersistenceMapper::toDomainModel);
    }

    //get user all notes
    @Override
    public List<Note> getAllNotesByUserId(Long userId){
        return   jpaNoteRepository.findByUser_UserId(userId).stream().map(notePersistenceMapper::toDomainModel).toList();
    }

    //create note
    @Override
    public Note createNote(Note note){
        NoteEntity toEntity = notePersistenceMapper.toEntity(note);
        NoteEntity savedNote = jpaNoteRepository.save(toEntity);

        return notePersistenceMapper.toDomainModel(savedNote);
    }

    //update note
    @Override
    public Note updateNote(Long noteId, Note note){
        //check existing note availability
        NoteEntity existingNoteEntity = jpaNoteRepository.findById(noteId)
                .orElseThrow(() -> new RuntimeException("Note not found"));
        //update note
        NoteEntity updatedNoteEntity = notePersistenceMapper.updateNoteEntity(note, existingNoteEntity);
        //save note
        NoteEntity savedNoteEntity = jpaNoteRepository.save(updatedNoteEntity);

        return notePersistenceMapper.toDomainModel(savedNoteEntity);
    }

    //delete note
    @Override
    public void deleteNote(Long noteId){
        NoteEntity existingNote = jpaNoteRepository.findById(noteId)
                .orElseThrow(() -> new RuntimeException("Note not found" +  " , " + noteId));

        jpaNoteRepository.delete(existingNote);
    }

    //testing update note
    @Override
    public Note testUpdateNote(Note existingNote, Note note) {
        NoteEntity checkNoteEntity = jpaNoteRepository.findById(existingNote.getNoteId())
                .orElseThrow(() -> new RuntimeException("Note not found"));
        NoteEntity updatedNoteEntity = notePersistenceMapper.updateNoteEntity(note,  checkNoteEntity);
        NoteEntity savedNoteEntity = jpaNoteRepository.save(updatedNoteEntity);
        return notePersistenceMapper.toDomainModel(savedNoteEntity);
    }
}
