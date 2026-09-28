package lk.spring_security.cookie_based_jwt_auth.infrastructure._configs._persistenceBeanConfig;

import lk.spring_security.cookie_based_jwt_auth.domain.repositories.IdentityManager;
import lk.spring_security.cookie_based_jwt_auth.domain.repositories.NoteRepository;
import lk.spring_security.cookie_based_jwt_auth.domain.repositories.UserRepository;
import lk.spring_security.cookie_based_jwt_auth.infrastructure._security.IdentityManagerImpl;
import lk.spring_security.cookie_based_jwt_auth.infrastructure.note.persistence.NoteRepositoryImpl;
import lk.spring_security.cookie_based_jwt_auth.infrastructure.note.persistence.jpa.JpaNoteRepository;
import lk.spring_security.cookie_based_jwt_auth.infrastructure.note.persistence.persistenceMapper.NotePersistenceMapper;
import lk.spring_security.cookie_based_jwt_auth.infrastructure.user.persistence.UserRepositoryImpl;
import lk.spring_security.cookie_based_jwt_auth.infrastructure.user.persistence.jpa.JpaUserRepository;
import lk.spring_security.cookie_based_jwt_auth.infrastructure.user.persistence.persistenceMapper.UserPersistenceMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;

@Configuration
public class PersistenceBeanConfigs {

    /* __IDENTITY_MANAGER_IMPL__ */

    @Bean
    public IdentityManager identityManager(
            AuthenticationManager authenticationManager
    ) {
        return new IdentityManagerImpl(authenticationManager);
    }

    /* __USER__ */

    @Bean
    public UserRepository userRepository(
            JpaUserRepository jpaUserRepository,
            UserPersistenceMapper userPersistenceMapper
    ) {
        return new UserRepositoryImpl(jpaUserRepository, userPersistenceMapper);
    }

    /* __NOTE__ */

    @Bean
    public NoteRepository noteRepository(
            JpaNoteRepository jpaNoteRepository,
            NotePersistenceMapper notePersistenceMapper
    ) {
        return new NoteRepositoryImpl(jpaNoteRepository, notePersistenceMapper);
    }
}
