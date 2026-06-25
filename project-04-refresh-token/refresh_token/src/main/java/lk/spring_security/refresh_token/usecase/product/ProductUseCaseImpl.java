package lk.spring_security.refresh_token.usecase.product;

import lk.spring_security.refresh_token.domain.repositories.ProductRepository;

public class ProductUseCaseImpl implements  ProductUseCase {

    //inject required dependencies
    private final  ProductRepository productRepository;

    public ProductUseCaseImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
}
