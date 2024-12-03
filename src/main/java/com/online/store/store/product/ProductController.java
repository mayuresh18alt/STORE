package com.online.store.store.product;

import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/add")
    ResponseEntity<?>addProduct(
                        @ModelAttribute("name") String name,
                        @ModelAttribute("brand") String brand,
                        @ModelAttribute("category") String category,
                        @ModelAttribute("description") String description,
                        @ModelAttribute("quantity") Integer quantity,
                        @ModelAttribute("price") Double price,
                        @RequestParam("image") MultipartFile file) throws IOException {

        Product product = new Product();
        product.setName(name);
        product.setBrand(brand);
        product.setCategory(category);
        product.setDescription(description);
        product.setQuantity(quantity);
        product.setPrice(price);
        product = productService.addProduct(product, file);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<Page<ProductDTO>> getAllProducts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ?
                Sort.by(sortBy).ascending() :
                Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<ProductDTO> productPage = productService.getProducts(pageable);

        return new ResponseEntity<>(productPage, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    ResponseEntity<?> updateProduct(@PathVariable("id") Long id,
                                    @ModelAttribute("name") String name,
                                    @ModelAttribute("brand") String brand,
                                    @ModelAttribute("category") String category,
                                    @ModelAttribute("description") String description,
                                    @ModelAttribute("quantity") Integer quantity,
                                    @ModelAttribute("price") Double price,
                                    @RequestParam(value = "image", required = false) MultipartFile file) throws IOException {

        Product product = productService.getProductById(id);
        product.setName(name);
        product.setBrand(brand);
        product.setCategory(category);
        product.setDescription(description);
        product.setQuantity(quantity);
        product.setPrice(price);

        // Update the image only if a new one is provided
        if (file != null && !file.isEmpty()) {
            product = productService.updateProduct(product, file);
        } else {
            product = productService.updateProduct(product, null);
        }

        return new ResponseEntity<>("Product updated successfully", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<?>deleteProduct(@PathVariable("id") Long id){
        productService.deleteProduct(productService.getProductById(id));
        return new ResponseEntity<>("Product deleted successfully",HttpStatus.OK);
    }


}