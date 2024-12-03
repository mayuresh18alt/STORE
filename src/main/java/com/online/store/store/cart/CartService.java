package com.online.store.store.cart;

import org.springframework.stereotype.Service;
import com.online.store.store.product.Product;
import java.util.List;
import java.util.ArrayList;

@Service
public class CartService {

    // Simulated cart storage (replace with actual DB logic)
    private final static List<Cart> carts = new ArrayList<>();

    // Get the cart for a specific user
    public List<Product> getCartByUser(String username) {
        // Search for the cart by username
        return carts.stream()
                .filter(cart -> cart.getUsername().equals(username))
                .map(Cart::getProducts)
                .findFirst()
                .orElse(new ArrayList<>());
    }

    // Add product to the user's cart
    public void addProductToCart(String username, Product product) {
        Cart cart = carts.stream()
                .filter(c -> c.getUsername().equals(username))
                .findFirst()
                .orElseGet(() -> {
                    Cart newCart = new Cart(username);
                    carts.add(newCart);
                    return newCart;
                });
        cart.getProducts().add(product);  // Add the product to the cart
    }

    // Remove product from the user's cart
    public void removeProductFromCart(String username, Long productId) {
        Cart cart = carts.stream()
                .filter(c -> c.getUsername().equals(username))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Cart not found"));

        cart.getProducts().removeIf(product -> product.getId().equals(productId));  // Remove product by id
    }
}
