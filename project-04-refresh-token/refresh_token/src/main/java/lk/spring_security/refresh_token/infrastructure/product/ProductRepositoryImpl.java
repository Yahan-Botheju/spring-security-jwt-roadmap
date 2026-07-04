package lk.spring_security.refresh_token.infrastructure.product;

import lk.spring_security.refresh_token.domain.models.Product;
import lk.spring_security.refresh_token.domain.repositories.ProductRepository;
import lk.spring_security.refresh_token.infrastructure.product.entities.ProductEntity;
import lk.spring_security.refresh_token.infrastructure.product.jpa.JpaProductRepository;
import lk.spring_security.refresh_token.infrastructure.product.mappers.ProductPersistenceMapper;

import java.util.Optional;

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

    //get product by id
    @Override
    public Optional<Product> productFindById(Long productId) {
        return jpaProductRepository.productFindById(productId);
    }

    //save products
    @Override
    public Product saveProducts(Product product) {
        if(jpaProductRepository.existsById(product.getProductId())){
            throw new RuntimeException("Product already exists");
        }

        ProductEntity toEntity = productPersistenceMapper.toEntity(product);
        ProductEntity savedEntity = jpaProductRepository.save(toEntity);

        return productPersistenceMapper.toDomainModel(savedEntity);
    }
}
