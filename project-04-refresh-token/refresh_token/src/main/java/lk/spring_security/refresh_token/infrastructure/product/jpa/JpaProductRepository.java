package lk.spring_security.refresh_token.infrastructure.product.jpa;

import lk.spring_security.refresh_token.infrastructure.product.entities.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaProductRepository extends JpaRepository<ProductEntity, Long>
{
}
