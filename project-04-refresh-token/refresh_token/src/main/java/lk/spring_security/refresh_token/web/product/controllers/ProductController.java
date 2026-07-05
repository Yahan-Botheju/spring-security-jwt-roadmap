package lk.spring_security.refresh_token.web.product.controllers;

import jakarta.validation.Valid;
import lk.spring_security.refresh_token.domain.models.Product;
import lk.spring_security.refresh_token.infrastructure._security.user_spring_wrapper.CustomUserDetails;
import lk.spring_security.refresh_token.usecase.product.ProductUseCase;
import lk.spring_security.refresh_token.web.product.DTOs.ProductRequestDTO;
import lk.spring_security.refresh_token.web.product.DTOs.ProductResponseDTO;
import lk.spring_security.refresh_token.web.product.webMappers.ProductWebMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/v1/products/")
public class ProductController {

    //inject required dependencies
    private final ProductUseCase productUseCase;
    private final ProductWebMapper productWebMapper;

    public ProductController(
            ProductUseCase productUseCase,
            ProductWebMapper productWebMapper
    ) {
        this.productUseCase = productUseCase;
        this.productWebMapper = productWebMapper;
    }

    //create product
     @PostMapping
     public ResponseEntity<ProductResponseDTO> createProduct(
             @Valid @RequestBody ProductRequestDTO productRequestDTO
             ){

        Product toDomainModel = productWebMapper.toDomainModel(productRequestDTO);
        Product toUseCase = productUseCase.createProduct(toDomainModel);
        ProductResponseDTO toResponse =  productWebMapper.toResponseDTO(toUseCase);

        return ResponseEntity.created(URI.create("/api/v1/products")).body(toResponse);
     }

     //update products
    @PostMapping
    public ResponseEntity<ProductResponseDTO> updateProduct(
            @Valid @RequestParam Long productId,
            @Valid @RequestBody ProductRequestDTO productRequestDTO
    ){
        Product toDomainModel = productWebMapper.toDomainModel(productRequestDTO);
        Product toUseCase = productUseCase.updateProducts(productId, toDomainModel);
        ProductResponseDTO toResponse =  productWebMapper.toResponseDTO(toUseCase);

        return ResponseEntity.ok(toResponse);
    }
}
