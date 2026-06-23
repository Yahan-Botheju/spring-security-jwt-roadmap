package lk.spring_security.refresh_token.web.product.webMappers;

import lk.spring_security.refresh_token.domain.models.Product;
import lk.spring_security.refresh_token.web.product.DTOs.ProductRequestDTO;
import lk.spring_security.refresh_token.web.product.DTOs.ProductResponseDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductWebMapper {
    //request to domain model
    Product toDomainModel(ProductRequestDTO productRequestDTO);

    //domain model to responseDTO
    ProductResponseDTO toResponseDTO(Product product);
}
