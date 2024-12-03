package com.online.store.store.product;


import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {
    Product addProduct(Product product, MultipartFile file) throws IOException;

    Page<ProductDTO> getProducts(Pageable pageable);

    Product updateProduct(Product product, MultipartFile file) throws IOException;

    Product deleteProduct(Product product);

    Product getProductById(Long id);
}
