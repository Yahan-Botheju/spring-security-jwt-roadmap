package lk.spring_security.stateful_jwt_refresh_token_rotation.infrastructure.persistence.refresh_token.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Entity
@Table(name = "refresh_tokens")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RefreshTokenEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tokenId;

    @Column(unique = true,nullable = false)
    private String token;

    @Column(nullable = false, name = "expiry_date")
    private Instant expiryDate;

    @Column(nullable = false, name = "is_used")
    private boolean isUsed;

    @Column(nullable = false, name = "is_revoked")
    private boolean isRevoked;
}
