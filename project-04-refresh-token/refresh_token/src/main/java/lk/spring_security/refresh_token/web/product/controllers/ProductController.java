package lk.spring_security.refresh_token.web.product.controllers;

import jakarta.validation.Valid;
import lk.spring_security.refresh_token.domain.models.Product;
import lk.spring_security.refresh_token.usecase.product.ProductUseCase;
import lk.spring_security.refresh_token.usecase.product.records.ProductCommand;
import lk.spring_security.refresh_token.usecase.product.records.ProductResult;
import lk.spring_security.refresh_token.web.product.DTOs.ProductRequestDTO;
import lk.spring_security.refresh_token.web.product.DTOs.ProductResponseDTO;
import lk.spring_security.refresh_token.web.product.DTOs.UpdateProductRequestDTO;
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

        List<ProductResult> productList = productUseCase.getAllProducts().stream().toList();
        return productList.stream().map(productWebMapper::toProductResponseDTO).toList();
    }

    //create product
     @PostMapping
     public ResponseEntity<ProductResponseDTO> createProduct(
             @Valid @RequestBody ProductRequestDTO productRequestDTO
             ){
         ProductCommand toCommand = productWebMapper.toProductCommand(productRequestDTO);
         ProductResult toUseCase = productUseCase.createProduct(toCommand);
         ProductResponseDTO toResponse = productWebMapper.toProductResponseDTO(toUseCase);

        return ResponseEntity.created(URI.create("/api/v1/products")).body(toResponse);
     }

     //update products
    @PutMapping
    public ResponseEntity<ProductResponseDTO> updateProduct(
            @Valid @RequestBody UpdateProductRequestDTO updateProductRequestDTO
    ){


        return ResponseEntity.ok(toResponse);
    }
}
