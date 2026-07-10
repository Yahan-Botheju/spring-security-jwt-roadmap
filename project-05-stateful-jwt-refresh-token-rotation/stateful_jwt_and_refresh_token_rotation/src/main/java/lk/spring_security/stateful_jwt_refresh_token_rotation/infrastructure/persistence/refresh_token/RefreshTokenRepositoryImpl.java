package lk.spring_security.stateful_jwt_refresh_token_rotation.infrastructure.persistence.refresh_token;

import lk.spring_security.stateful_jwt_refresh_token_rotation.domain.repositories.RefreshTokenReposiroty;
import lk.spring_security.stateful_jwt_refresh_token_rotation.domain.repositories.UserRepository;
import lk.spring_security.stateful_jwt_refresh_token_rotation.infrastructure.persistence.refresh_token.jpa.JpaRefreshTokenRepository;
import lk.spring_security.stateful_jwt_refresh_token_rotation.infrastructure.persistence.refresh_token.mappers.RefreshTokenPersistenceMapper;

public class RefreshTokenRepositoryImpl implements RefreshTokenReposiroty {

    //inject required dependencies
    private final JpaRefreshTokenRepository jpaRefreshTokenRepository;
    private final RefreshTokenPersistenceMapper refreshTokenPersistenceMapper;
    private final UserRepository userRepository;



    public RefreshTokenRepositoryImpl(
            JpaRefreshTokenRepository jpaRefreshTokenRepository,
            RefreshTokenPersistenceMapper refreshTokenPersistenceMapper,
            UserRepository userRepository
    ) {
        this.jpaRefreshTokenRepository = jpaRefreshTokenRepository;
        this.userRepository = userRepository;
        this.refreshTokenPersistenceMapper = refreshTokenPersistenceMapper;
    }

}
