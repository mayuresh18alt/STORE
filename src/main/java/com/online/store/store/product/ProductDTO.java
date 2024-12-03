package com.online.store.store.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
    private Long id;
    private String name;
    private String brand;
    private String category;
    private String description;
    private Integer quantity;
    private Double price;
    private String image; // Base64 encoded string
}

