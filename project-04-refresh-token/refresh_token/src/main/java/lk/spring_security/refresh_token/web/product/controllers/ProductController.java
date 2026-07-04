package lk.spring_security.refresh_token.web.product.controllers;

import lk.spring_security.refresh_token.usecase.product.ProductUseCase;
import lk.spring_security.refresh_token.web.product.webMappers.ProductWebMapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
