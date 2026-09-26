package lk.spring_security.refresh_token.usecase.product;

import lk.spring_security.refresh_token.domain.models.Product;
import lk.spring_security.refresh_token.domain.repositories.ProductRepository;
import lk.spring_security.refresh_token.usecase.product.records.ProductCommand;
import lk.spring_security.refresh_token.usecase.product.records.ProductResult;

import java.util.List;
import java.util.stream.Collectors;

public class ProductUseCaseImpl implements ProductUseCase {

    //inject required dependencies
    private final ProductRepository productRepository;

    public ProductUseCaseImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    //get all products
    @Override
    public List<ProductResult> getAllProducts() {
        return productRepository.getAllProducts()
                .stream().
                map(product ->
                        new ProductResult(
                        product.getProductId(),
                        product.getProductName(),
                        product.getProductPrice()
                )).collect(Collectors.toList());


    }

    //create product
    @Override
    public ProductResult createProduct(ProductCommand productCommand) {
        //check incoming fields
        if(productCommand.productName().isBlank() || productCommand.productPrice() == 0){
            throw new IllegalStateException("Product name and product price cannot be empty");
        }

        //create product model
        Product newProduct = Product.createNewProduct(
                productCommand.productName(),
                productCommand.productPrice()
        );

        productRepository.saveProducts(newProduct);

        return new ProductResult(
                newProduct.getProductId(),
                newProduct.getProductName(),
                newProduct.getProductPrice()
        );
    }

    //update products
    @Override
    public Product updateProducts(Long productId, Product product) {
        if (!productRepository.productFindById(productId).isPresent()) {
            throw new IllegalArgumentException("Product doesn't exists");
        }

        return productRepository.updateProducts(productId, product);
    }
}
