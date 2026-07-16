package lk.spring_security.stateful_jwt_refresh_token_rotation.infrastructure.persistence.refresh_token;

import lk.spring_security.stateful_jwt_refresh_token_rotation.domain.models.RefreshToken;
import lk.spring_security.stateful_jwt_refresh_token_rotation.domain.repositories.RefreshTokenRepository;
import lk.spring_security.stateful_jwt_refresh_token_rotation.domain.repositories.UserRepository;
import lk.spring_security.stateful_jwt_refresh_token_rotation.infrastructure.persistence.refresh_token.entities.RefreshTokenEntity;
import lk.spring_security.stateful_jwt_refresh_token_rotation.infrastructure.persistence.refresh_token.jpa.JpaRefreshTokenRepository;
import lk.spring_security.stateful_jwt_refresh_token_rotation.infrastructure.persistence.refresh_token.mappers.RefreshTokenPersistenceMapper;

import java.util.Optional;

public class RefreshTokenRepositoryImpl implements RefreshTokenRepository {

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

    //find token
    @Override
    public Optional<RefreshToken> findByToken(String token) {
        return jpaRefreshTokenRepository.findByToken(token)
                .map(refreshTokenPersistenceMapper::toDomainModel);
    }

    //save refresh token
    @Override
    public RefreshToken  saveRefreshToken(RefreshToken refreshToken) {
        RefreshTokenEntity toEntity = refreshTokenPersistenceMapper.toEntity(refreshToken);
        RefreshTokenEntity savedTokenEntity = jpaRefreshTokenRepository.save(toEntity);

        return refreshTokenPersistenceMapper.toDomainModel(savedTokenEntity);
    }

    //revoke user all tokens
    @Override
    public void revokeAllUserTokens(Long userId) {
        if (jpaRefreshTokenRepository.findById(userId).isEmpty()) {
            throw new RuntimeException("user not found");
        }
        jpaRefreshTokenRepository.revokeRefreshToken(userId);
    }
}
