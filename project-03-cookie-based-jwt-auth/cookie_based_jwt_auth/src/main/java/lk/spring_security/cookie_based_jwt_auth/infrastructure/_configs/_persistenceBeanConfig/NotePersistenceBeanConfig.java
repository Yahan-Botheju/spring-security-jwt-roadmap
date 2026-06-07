package lk.spring_security.cookie_based_jwt_auth.infrastructure._configs._persistenceBeanConfig;

import lk.spring_security.cookie_based_jwt_auth.domain.repositories.NoteRepository;
import lk.spring_security.cookie_based_jwt_auth.infrastructure.note.persistence.NoteRepositoryImpl;
import lk.spring_security.cookie_based_jwt_auth.infrastructure.note.persistence.jpa.JpaNoteRepository;
import lk.spring_security.cookie_based_jwt_auth.infrastructure.note.persistence.persistenceMapper.NotePersistenceMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class NotePersistenceBeanConfig {
    @Bean
    public NoteRepository noteRepository(
            JpaNoteRepository jpaNoteRepository,
            NotePersistenceMapper notePersistenceMapper
    ) {
        return new NoteRepositoryImpl(jpaNoteRepository, notePersistenceMapper);
    }
}
