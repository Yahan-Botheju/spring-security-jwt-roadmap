package lk.spring_security.cookie_based_jwt_auth.infrastructure.note.persistence;

import lk.spring_security.cookie_based_jwt_auth.domain.repositories.NoteRepository;
import lk.spring_security.cookie_based_jwt_auth.infrastructure.note.persistence.jpa.JpaNoteRepository;
import lk.spring_security.cookie_based_jwt_auth.infrastructure.note.persistence.persistenceMapper.NotePersistenceMapper;
import lk.spring_security.method_level_security_global_security_exceptions.domain.repositories.TaskRepository;

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
}
