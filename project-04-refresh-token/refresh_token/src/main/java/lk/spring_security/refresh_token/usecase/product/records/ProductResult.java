package lk.spring_security.refresh_token.usecase.product.records;

public record ProductResult(
        Long  productId,
        String productName,
        Double productPrice
) {
}
