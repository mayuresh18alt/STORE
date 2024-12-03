package com.online.store.store.cart;

import com.online.store.store.product.Product;
import java.util.List;
import java.util.ArrayList;

public class Cart {

    private String username;
    private List<Product> products;

    public Cart(String username) {
        this.username = username;
        this.products = new ArrayList<>();
    }

    public String getUsername() {
        return username;
    }

    public List<Product> getProducts() {
        return products;
    }
}
