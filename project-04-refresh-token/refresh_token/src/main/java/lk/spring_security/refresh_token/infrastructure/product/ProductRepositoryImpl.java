package lk.spring_security.refresh_token.infrastructure.product;

import lk.spring_security.refresh_token.domain.repositories.ProductRepository;
import lk.spring_security.refresh_token.infrastructure.product.jpa.JpaProductRepository;
import lk.spring_security.refresh_token.infrastructure.product.mappers.ProductPersistenceMapper;

public class ProductRepositoryImpl implements ProductRepository {

    //inject required dependencies
    private final JpaProductRepository jpaProductRepository;
    private final ProductPersistenceMapper productPersistenceMapper;


    public ProductRepositoryImpl(
            JpaProductRepository jpaProductRepository,
            ProductPersistenceMapper productPersistenceMapper
    ) {
        this.jpaProductRepository = jpaProductRepository;
        this.productPersistenceMapper = productPersistenceMapper;
    }
}
