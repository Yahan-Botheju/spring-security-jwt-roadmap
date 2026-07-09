package lk.spring_security.stateful_jwt_refresh_token_rotation.infrastructure.persistence.wallet.entities;

import jakarta.persistence.*;
import lk.spring_security.stateful_jwt_refresh_token_rotation.infrastructure.persistence.user.entities.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "wallets")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WalletEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long walletId;

    @Column(nullable = false)
    private Double walletBalance;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "userId", nullable = false)
    private UserEntity user;

}
