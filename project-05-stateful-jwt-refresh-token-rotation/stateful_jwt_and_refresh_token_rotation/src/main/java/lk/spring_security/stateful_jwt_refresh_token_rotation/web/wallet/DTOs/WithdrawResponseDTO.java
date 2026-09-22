package lk.spring_security.stateful_jwt_refresh_token_rotation.web.wallet.DTOs;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WithdrawResponseDTO {

    private String email;
    private Double walletBalance;
}
