package lk.spring_security.refresh_token.web.product.DTOs;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequestDTO {
    @NotBlank(message = "Product name cannot be empty")
    private String productName;

    @NotNull(message = "Price cannot be empty")
    @Positive(message = "Price must be positive value")
    private double productPrice;
}
