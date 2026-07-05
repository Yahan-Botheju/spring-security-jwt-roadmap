package lk.spring_security.refresh_token.usecase.product;

import lk.spring_security.refresh_token.domain.models.Product;

public interface ProductUseCase {

    //create product
    Product createProduct(Product product);

    //update products
    Product updateProducts(Long productId, Product product);
}
