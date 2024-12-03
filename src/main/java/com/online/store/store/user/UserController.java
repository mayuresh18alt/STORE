package com.online.store.store.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import com.online.store.store.cart.CartService;
import com.online.store.store.cart.Cart;
import com.online.store.store.product.Product;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private CartService cartService;

    // Get user details
    @GetMapping("/user")
    public ResponseEntity<UserDetails> getUserDetails(Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return ResponseEntity.ok(userDetails);
    }

    // Get cart items for the logged-in user
    @GetMapping("/cart")
    public ResponseEntity<List<Product>> getCart(Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String username = userDetails.getUsername();  // Assuming username is unique, can be used as userId
        List<Product> cartItems = cartService.getCartByUser(username);  // Call service to get user's cart
        return ResponseEntity.ok(cartItems);
    }

    // Add a product to the user's cart
    @PostMapping("/cart")
    public ResponseEntity<String> addToCart(@RequestBody Product product, Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String username = userDetails.getUsername();  // Use username as userId
        cartService.addProductToCart(username, product);
        return ResponseEntity.ok("Product added to cart");
    }

    // Remove a product from the user's cart
    @DeleteMapping("/cart/{productId}")
    public ResponseEntity<String> removeFromCart(@PathVariable Long productId, Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String username = userDetails.getUsername();  // Use username as userId
        cartService.removeProductFromCart(username, productId);
        return ResponseEntity.ok("Product removed from cart");
    }
}
