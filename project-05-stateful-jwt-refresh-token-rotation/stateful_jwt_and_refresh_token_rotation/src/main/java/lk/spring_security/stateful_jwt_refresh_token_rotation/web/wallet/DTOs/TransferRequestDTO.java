package lk.spring_security.stateful_jwt_refresh_token_rotation.web.wallet.DTOs;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransferRequestDTO {
    @NotBlank(message = "Receiver email cannot be empty")
    private String toEmail;

    @NotNull(message = "Amount cannot be null")
    @Min(value = 1, message = "Minimum transfer amount is 1")
    private Double amount;
}
