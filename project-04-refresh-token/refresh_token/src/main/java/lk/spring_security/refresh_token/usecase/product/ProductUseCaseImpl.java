package lk.spring_security.refresh_token.usecase.product;

import lk.spring_security.refresh_token.domain.models.Product;
import lk.spring_security.refresh_token.domain.repositories.ProductRepository;

import java.util.List;

public class ProductUseCaseImpl implements  ProductUseCase {

    //inject required dependencies
    private final  ProductRepository productRepository;

    public ProductUseCaseImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    //get all products
    @Override
    public List<Product> getAllProducts(){
        return productRepository.getAllProducts().stream().toList();
    }


    //create product
    @Override
    public Product createProduct(Product product){
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
