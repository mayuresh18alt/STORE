package com.online.store.store.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;


    @Override
    public Product addProduct(Product product, MultipartFile file) throws IOException {
        byte[] imageData = file.getBytes();
        product.setImage(imageData);
        return productRepository.save(product);
    }

    @Override
    public Page<ProductDTO> getProducts(Pageable pageable) {
        Page<Product> productPage = productRepository.findAll(pageable);
        List<ProductDTO> productDTOs = productPage.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());

        return new PageImpl<>(productDTOs, pageable, productPage.getTotalElements());
    }

    private ProductDTO convertToDTO(Product product) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setName(product.getName());
        productDTO.setBrand(product.getBrand());
        productDTO.setCategory(product.getCategory());
        productDTO.setDescription(product.getDescription());
        productDTO.setQuantity(product.getQuantity());
        productDTO.setPrice(product.getPrice());

        // Convert the byte array to a Base64 encoded string
        String base64Image = Base64.getEncoder().encodeToString(product.getImage());
        productDTO.setImage(base64Image);

        return productDTO;
    }



    @Override
    public Product updateProduct(Product product, MultipartFile file) throws IOException {
        if (file != null && !file.isEmpty()) {
            // Convert the file to a byte array and update the product's image
            byte[] imageBytes = file.getBytes();
            product.setImage(imageBytes);
        }
        // Save the product in the database
        return productRepository.save(product);
    }

    public Product updateProduct(Product product) {
        // Update other fields without modifying the image
        return productRepository.save(product);
    }


    @Override
    public Product deleteProduct(Product product) {
        if(productRepository.existsById(product.getId())){
            productRepository.delete(product);
            return product;
        }else{
            return null;
        }
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id).get();
    }
}