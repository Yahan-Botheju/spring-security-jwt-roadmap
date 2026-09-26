package lk.spring_security.refresh_token.web.product.DTOs;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateProductRequestDTO {
    @NotNull(message = "Product ID cannot be empty")
    private Long  productId;
    @NotBlank(message = "Product name cannot be empty")
    private String productName;
    @NotNull(message = "Product price cannot be empty")
    private Double productPrice;
}
