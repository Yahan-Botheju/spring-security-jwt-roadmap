package lk.spring_security.refresh_token.domain.repositories;

import lk.spring_security.refresh_token.domain.models.Product;

import java.util.Optional;

public interface ProductRepository {

    //product find by id
    Optional<Product> productFindById(Long productId);

    //save products
    Product saveProducts(Product product);

    //update products
    Product updateProducts(Long productId, Product product);
}
