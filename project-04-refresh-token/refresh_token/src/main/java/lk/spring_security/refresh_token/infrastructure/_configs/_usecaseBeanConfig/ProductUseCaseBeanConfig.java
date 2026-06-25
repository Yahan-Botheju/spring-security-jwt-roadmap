package lk.spring_security.refresh_token.infrastructure._configs._usecaseBeanConfig;

import lk.spring_security.refresh_token.domain.repositories.ProductRepository;
import lk.spring_security.refresh_token.usecase.product.ProductUseCase;
import lk.spring_security.refresh_token.usecase.product.ProductUseCaseImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProductUseCaseBeanConfig {
    @Bean
    public ProductUseCase  productUseCase(
            ProductRepository productRepository
    ) {
        return new ProductUseCaseImpl(productRepository);
    }
}
