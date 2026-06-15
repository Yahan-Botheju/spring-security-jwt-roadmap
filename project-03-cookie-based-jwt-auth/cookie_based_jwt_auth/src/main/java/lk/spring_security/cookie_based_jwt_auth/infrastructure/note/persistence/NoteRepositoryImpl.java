package lk.spring_security.cookie_based_jwt_auth.infrastructure.note.persistence;

import lk.spring_security.cookie_based_jwt_auth.domain.models.Note;
import lk.spring_security.cookie_based_jwt_auth.domain.repositories.NoteRepository;
import lk.spring_security.cookie_based_jwt_auth.infrastructure.note.persistence.entities.NoteEntity;
import lk.spring_security.cookie_based_jwt_auth.infrastructure.note.persistence.jpa.JpaNoteRepository;
import lk.spring_security.cookie_based_jwt_auth.infrastructure.note.persistence.persistenceMapper.NotePersistenceMapper;

import java.util.List;

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
}
