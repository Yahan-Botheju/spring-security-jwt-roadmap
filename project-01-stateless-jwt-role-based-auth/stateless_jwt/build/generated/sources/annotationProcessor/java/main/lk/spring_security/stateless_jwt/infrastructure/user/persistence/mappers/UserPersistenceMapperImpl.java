package lk.spring_security.stateless_jwt.infrastructure.user.persistence.mappers;

import javax.annotation.processing.Generated;
import lk.spring_security.stateless_jwt.domain.models.User;
import lk.spring_security.stateless_jwt.infrastructure.user.persistence.entities.UserEntity;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-05-24T12:44:59+0530",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-9.4.1.jar, environment: Java 17.0.18 (Microsoft)"
)
@Component
public class UserPersistenceMapperImpl implements UserPersistenceMapper {

    @Override
    public UserEntity toEntity(User user) {
        if ( user == null ) {
            return null;
        }

        UserEntity.UserEntityBuilder userEntity = UserEntity.builder();

        userEntity.userId( user.getUserId() );
        userEntity.email( user.getEmail() );
        userEntity.password( user.getPassword() );
        userEntity.role( user.getRole() );

        return userEntity.build();
    }

    @Override
    public User toDomainModel(UserEntity userEntity) {
        if ( userEntity == null ) {
            return null;
        }

        User.UserBuilder user = User.builder();

        user.userId( userEntity.getUserId() );
        user.email( userEntity.getEmail() );
        user.password( userEntity.getPassword() );
        user.role( userEntity.getRole() );

        return user.build();
    }

    @Override
    public UserEntity updateUser(User user, UserEntity userEntity) {
        if ( user == null ) {
            return userEntity;
        }

        if ( user.getUserId() != null ) {
            userEntity.setUserId( user.getUserId() );
        }
        if ( user.getEmail() != null ) {
            userEntity.setEmail( user.getEmail() );
        }
        if ( user.getPassword() != null ) {
            userEntity.setPassword( user.getPassword() );
        }
        if ( user.getRole() != null ) {
            userEntity.setRole( user.getRole() );
        }

        return userEntity;
    }
}
