package lk.spring_security.refresh_token.domain.models;

public class Product {
    private Long productId;
    private String productName;
    private Double productPrice;

    public Product(Long productId, String productName, Double productPrice) {
        this.productId = productId;
        this.productName = productName;
        this.productPrice = productPrice;
    }

    public Long getProductId() { return productId; }

    public String getProductName() { return productName; }

    public Double getProductPrice() { return productPrice; }
}
