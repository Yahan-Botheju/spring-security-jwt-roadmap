package lk.spring_security.refresh_token.web.product.controllers;

import jakarta.validation.Valid;
import lk.spring_security.refresh_token.domain.models.Product;
import lk.spring_security.refresh_token.usecase.product.ProductUseCase;
import lk.spring_security.refresh_token.web.product.DTOs.ProductRequestDTO;
import lk.spring_security.refresh_token.web.product.DTOs.ProductResponseDTO;
import lk.spring_security.refresh_token.web.product.webMappers.ProductWebMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
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

    //get all products
    @GetMapping
    public List<ProductResponseDTO> getAllProducts(){
        List<Product> productList = productUseCase.getAllProducts().stream().toList();

        return productList.stream().map(productWebMapper::toResponseDTO).toList();
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
    @PutMapping
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
