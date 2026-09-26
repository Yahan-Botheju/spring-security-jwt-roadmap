package lk.spring_security.refresh_token.usecase.product.records;

public record UpdateProductResult(
        Long  productId,
        String productName,
        Double productPrice
) {
}
