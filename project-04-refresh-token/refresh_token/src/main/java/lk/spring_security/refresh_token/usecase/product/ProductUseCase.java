package lk.spring_security.refresh_token.usecase.product;

import lk.spring_security.refresh_token.domain.models.Product;

import java.util.List;

public interface ProductUseCase {

    //get all products
    List<Product> getAllProducts();

    //create product
    Product createProduct(Product product);

    //update products
    Product updateProducts(Long productId, Product product);
}
