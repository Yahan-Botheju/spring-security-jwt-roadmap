package lk.spring_security.stateful_jwt_refresh_token_rotation.infrastructure.persistence.wallet.jpa;

import lk.spring_security.stateful_jwt_refresh_token_rotation.infrastructure.persistence.wallet.entities.WalletEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaWalletRepository extends JpaRepository<WalletEntity, Long> {
}
