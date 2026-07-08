package lk.spring_security.stateful_jwt_refresh_token_rotation.domain.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RefreshToken {
    private Long tokenId;
    private String token;
    private Instant expiryDate;

    //reply detection
    private boolean isUsed;
    private boolean isRevoked;

    private User user;
}
