package lk.spring_security.refresh_token.usecase.product.records;

public record ProductCommand(
        String productName,
        double productPrice
) {
}
