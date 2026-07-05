package lk.spring_security.refresh_token.usecase.product;

import lk.spring_security.refresh_token.domain.models.Product;
import lk.spring_security.refresh_token.domain.repositories.ProductRepository;

public class ProductUseCaseImpl implements  ProductUseCase {

    //inject required dependencies
    private final  ProductRepository productRepository;

    public ProductUseCaseImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    //create product
    @Override
    public Product createProduct(Product product){
        if(productRepository.productFindById(product.getProductId()).isPresent()){
            throw new IllegalArgumentException("Product already exists");
        }
        return productRepository.saveProducts(product);
    }

    //update products
    @Override
    public Product updateProducts(Long productId, Product product){
       if(!productRepository.productFindById(productId).isPresent()){
           throw new IllegalArgumentException("Product doesn't exists");
       }

        return productRepository.updateProducts(productId, product);
    }
}
