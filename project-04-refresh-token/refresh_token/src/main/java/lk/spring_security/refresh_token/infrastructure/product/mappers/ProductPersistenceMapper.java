package lk.spring_security.refresh_token.infrastructure.product.mappers;

import lk.spring_security.refresh_token.domain.models.Product;
import lk.spring_security.refresh_token.infrastructure.product.entities.ProductEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ProductPersistenceMapper {
    //domain model to entity
    ProductEntity toEntity(Product product);

    //entity to domain model
    Product toDomainModel(ProductEntity  productEntity);

    //update product
    ProductEntity updateEntity(Product product, @MappingTarget ProductEntity productEntity);
}
