package lk.spring_security.stateful_jwt_refresh_token_rotation.domain.models;

import lombok.Builder;
import lombok.Getter;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Getter
@Builder
public class RefreshToken {
    private Long tokenId;
    private String token;
    private Instant expiryDate;

    //reply detection
    private boolean isUsed;
    private boolean isRevoked;

    private User user;

    public RefreshToken(Long tokenId, String token, Instant expiryDate, boolean isUsed, boolean isRevoked, User user) {
        this.tokenId = tokenId;
        this.token = token;
        this.expiryDate = expiryDate;
        this.isUsed = isUsed;
        this.isRevoked = isRevoked;
        this.user = user;
    }

    /* __CREATE_FACTORY_METHOD__ */

    public static RefreshToken createNewRefreshToken(User user, String refreshToken){
        return RefreshToken.builder()
                .token(refreshToken)
                .expiryDate(Instant.now().plus(7, ChronoUnit.DAYS))
                .isUsed(false)
                .isRevoked(false)
                .user(user)
                .build();
    }

    /* __DOMAIN_LOGIC__ */

    //refresh token marking
    public void markAsUsed() {
        this.isUsed = true;
    }

    //refresh token revoked
    public void markAsRevoked() {
        this.isRevoked = true;
    }


}
