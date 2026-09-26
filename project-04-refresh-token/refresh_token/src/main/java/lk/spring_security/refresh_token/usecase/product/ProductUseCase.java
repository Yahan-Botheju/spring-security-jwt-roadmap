package lk.spring_security.refresh_token.usecase.product;

import lk.spring_security.refresh_token.usecase.product.records.ProductCommand;
import lk.spring_security.refresh_token.usecase.product.records.ProductResult;
import lk.spring_security.refresh_token.usecase.product.records.UpdateProductCommand;
import lk.spring_security.refresh_token.usecase.product.records.UpdateProductResult;

import java.util.List;

public interface ProductUseCase {

    //get all products
    List<ProductResult> getAllProducts();

    //create product
    ProductResult createProduct(ProductCommand productCommand);

    //update products
    UpdateProductResult updateProducts(UpdateProductCommand updateProductCommand);
}
