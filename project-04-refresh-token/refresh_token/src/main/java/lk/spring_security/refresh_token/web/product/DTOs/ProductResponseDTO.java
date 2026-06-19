package lk.spring_security.refresh_token.web.product.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponseDTO {
    private Long  productId;
    private String productName;
    private Double productPrice;
}
