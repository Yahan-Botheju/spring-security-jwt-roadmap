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

    /* __FACTORY_METHOD__ */

    public static Product createNewProduct(String productName, Double productPrice) {
        return new Product(null, productName, productPrice);
    }

    /* __UPDATE_MODEL__ */

    public void updateProduct(String productName, Double productPrice) {
        this.productName = productName;
        this.productPrice = productPrice;
    }
}
