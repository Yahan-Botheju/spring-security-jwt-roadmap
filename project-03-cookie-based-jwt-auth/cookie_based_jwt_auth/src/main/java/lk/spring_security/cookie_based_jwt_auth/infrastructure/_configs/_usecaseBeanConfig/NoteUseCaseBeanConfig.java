package lk.spring_security.cookie_based_jwt_auth.infrastructure._configs._usecaseBeanConfig;

import lk.spring_security.cookie_based_jwt_auth.domain.repositories.NoteRepository;
import lk.spring_security.cookie_based_jwt_auth.domain.repositories.UserRepository;
import lk.spring_security.cookie_based_jwt_auth.usecase.note.NoteUseCase;
import lk.spring_security.cookie_based_jwt_auth.usecase.note.NoteUseCaseImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class NoteUseCaseBeanConfig {

    @Bean
    public NoteUseCase noteUseCase(
            NoteRepository noteRepository,
            UserRepository userRepository
    ) {
        return new NoteUseCaseImpl(noteRepository, userRepository);
    }
}
