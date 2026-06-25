package lk.spring_security.refresh_token.infrastructure._configs._persistenceBeanConfig;

import lk.spring_security.refresh_token.domain.repositories.ProductRepository;
import lk.spring_security.refresh_token.infrastructure.product.ProductRepositoryImpl;
import lk.spring_security.refresh_token.infrastructure.product.jpa.JpaProductRepository;
import lk.spring_security.refresh_token.infrastructure.product.mappers.ProductPersistenceMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProductPersistenceBeanConfig {
    @Bean
    public ProductRepository productRepository(
            JpaProductRepository jpaProductRepository,
            ProductPersistenceMapper productPersistenceMapper
    ) {
        return new ProductRepositoryImpl(jpaProductRepository, productPersistenceMapper);
    }
}
