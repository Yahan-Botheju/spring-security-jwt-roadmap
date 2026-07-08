package lk.spring_security.stateful_jwt_refresh_token_rotation.domain.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Wallet {
    private Long walletId;
    private Double walletBalance;

    private User user;
}
