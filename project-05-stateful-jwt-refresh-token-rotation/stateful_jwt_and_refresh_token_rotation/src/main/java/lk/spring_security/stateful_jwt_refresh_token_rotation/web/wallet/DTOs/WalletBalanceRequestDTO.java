package lk.spring_security.stateful_jwt_refresh_token_rotation.web.wallet.DTOs;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WalletBalanceRequestDTO {
    @Email
    @NotBlank(message = "Email cannot be empty")
    private String email;
}
