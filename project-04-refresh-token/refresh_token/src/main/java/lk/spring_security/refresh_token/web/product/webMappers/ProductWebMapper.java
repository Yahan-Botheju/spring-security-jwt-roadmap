package lk.spring_security.refresh_token.web.product.webMappers;

import lk.spring_security.refresh_token.domain.models.Product;
import lk.spring_security.refresh_token.usecase.product.records.ProductCommand;
import lk.spring_security.refresh_token.usecase.product.records.ProductResult;
import lk.spring_security.refresh_token.usecase.product.records.UpdateProductCommand;
import lk.spring_security.refresh_token.usecase.product.records.UpdateProductResult;
import lk.spring_security.refresh_token.web.product.DTOs.ProductRequestDTO;
import lk.spring_security.refresh_token.web.product.DTOs.ProductResponseDTO;
import lk.spring_security.refresh_token.web.product.DTOs.UpdateProductResponseDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductWebMapper {
    //request to domain model
    Product toDomainModel(ProductRequestDTO productRequestDTO);

    //domain model to responseDTO
    ProductResponseDTO toResponseDTO(Product product);

    /* __PRODUCT_COMMON_MAPPERS__ */

    //requestDTO to usecase
    ProductCommand toProductCommand(ProductRequestDTO productRequestDTO);

    //domain model to responseDTO
    ProductResponseDTO toProductResponseDTO(ProductResult productResult);



    /* __PRODUCT_UPDATE__ */

    //requestDTO to usecase
    UpdateProductCommand toUpdateProductCommand(UpdateProductCommand updateProductCommand);

    //domain model to usecase
    UpdateProductResponseDTO toUpdateResponseDTO(UpdateProductResult updateProductResult);
}
