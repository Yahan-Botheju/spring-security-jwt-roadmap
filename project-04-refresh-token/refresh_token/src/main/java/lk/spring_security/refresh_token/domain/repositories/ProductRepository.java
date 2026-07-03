package lk.spring_security.refresh_token.domain.repositories;

import lk.spring_security.refresh_token.domain.models.Product;

public interface ProductRepository {

    //save products
    Product saveProducts(Product product);
}
