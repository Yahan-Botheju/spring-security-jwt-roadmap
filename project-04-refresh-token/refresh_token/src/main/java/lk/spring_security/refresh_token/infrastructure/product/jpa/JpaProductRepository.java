package lk.spring_security.refresh_token.infrastructure.product.jpa;

import lk.spring_security.refresh_token.domain.models.Product;
import lk.spring_security.refresh_token.infrastructure.product.entities.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaProductRepository extends JpaRepository<ProductEntity, Long> {

    //product find by id
    Optional<Product> productFindById(Long productId);


}
