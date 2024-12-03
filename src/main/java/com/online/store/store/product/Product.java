package com.online.store.store.product;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long id;
    @NotNull
    private String name;
    @NotNull
    private String brand;
    @NotNull
    private String category;
    @NotNull
    private String description;
    @NotNull
    private Integer quantity;
    @NotNull
    private Double price;
    @NotNull
    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private byte[] image;
}
