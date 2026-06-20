package lk.spring_security.refresh_token.infrastructure.refreshToken;

import lk.spring_security.refresh_token.domain.models.RefreshToken;
import lk.spring_security.refresh_token.domain.repositories.RefreshTokenRepository;
import lk.spring_security.refresh_token.infrastructure.refreshToken.entities.RefreshTokenEntity;
import lk.spring_security.refresh_token.infrastructure.refreshToken.jpa.JpaRefreshTokenRepository;
import lk.spring_security.refresh_token.infrastructure.refreshToken.mapper.RefreshTokenPersistenceMapper;

import java.util.Optional;

public class RefreshTokenRepositoryImpl implements RefreshTokenRepository {

    //inject required dependencies
    private final JpaRefreshTokenRepository jpaRefreshTokenRepository;
    private final RefreshTokenPersistenceMapper refreshTokenPersistenceMapper;


    public RefreshTokenRepositoryImpl(
            JpaRefreshTokenRepository jpaRefreshTokenRepository,
            RefreshTokenPersistenceMapper refreshTokenPersistenceMapper
    ) {
        this.jpaRefreshTokenRepository = jpaRefreshTokenRepository;
        this.refreshTokenPersistenceMapper = refreshTokenPersistenceMapper;
    }

    //check token availability
    @Override
    public Optional<RefreshToken> findByToken(String token){
        return jpaRefreshTokenRepository.findByToken(token)
                .map(refreshTokenPersistenceMapper::toDomainModel);
    }

    //save token in db
    @Override
    public RefreshToken saveToken(RefreshToken refreshToken){
        if(jpaRefreshTokenRepository.findByToken(refreshToken.getToken()).isPresent()){
            throw new IllegalArgumentException("Token already exists");
        }
        RefreshTokenEntity tokenEntity = refreshTokenPersistenceMapper.toEntity(refreshToken);
        RefreshTokenEntity savedToken = jpaRefreshTokenRepository.save(tokenEntity);

        return refreshTokenPersistenceMapper.toDomainModel(savedToken);
    }
}
