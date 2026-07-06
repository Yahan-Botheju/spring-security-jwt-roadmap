package lk.spring_security.refresh_token.infrastructure.product;

import lk.spring_security.refresh_token.domain.models.Product;
import lk.spring_security.refresh_token.domain.repositories.ProductRepository;
import lk.spring_security.refresh_token.infrastructure.product.entities.ProductEntity;
import lk.spring_security.refresh_token.infrastructure.product.jpa.JpaProductRepository;
import lk.spring_security.refresh_token.infrastructure.product.mappers.ProductPersistenceMapper;

import java.util.List;
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
        return jpaProductRepository.findById(productId).map(productPersistenceMapper::toDomainModel);
    }

    //get all products
    @Override
    public List<Product> getAllProducts(){
        List<ProductEntity> productEntities = jpaProductRepository.findAll();
        return productEntities.stream().map(productPersistenceMapper::toDomainModel).toList();
    }

    //save products
    @Override
    public Product saveProducts(Product product) {

        ProductEntity toEntity = productPersistenceMapper.toEntity(product);
        ProductEntity savedEntity = jpaProductRepository.save(toEntity);

        return productPersistenceMapper.toDomainModel(savedEntity);
    }

    //update products
    @Override
    public Product updateProducts(Long productId, Product product) {
        ProductEntity availableProduct = jpaProductRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        ProductEntity updatedProduct = productPersistenceMapper.updateEntity(product, availableProduct);
        ProductEntity savedProduct = jpaProductRepository.save(updatedProduct);

        return productPersistenceMapper.toDomainModel(savedProduct);
    }
}
