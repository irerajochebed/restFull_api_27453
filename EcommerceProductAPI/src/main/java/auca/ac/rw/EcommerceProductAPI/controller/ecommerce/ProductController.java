package auca.ac.rw.EcommerceProductAPI.controller.ecommerce;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import auca.ac.rw.EcommerceProductAPI.modal.ecommerce.Product;

@RestController
@RequestMapping(value = "/api/products")
public class ProductController {
    
    // In-memory product storage
    List<Product> products = new ArrayList<>();
    
    
    public ProductController() {
        // ELECTRONICS - 5 products
        products.add(new Product(1L, "iPhone 15 Pro", 
            "Latest Apple smartphone with A17 Pro", 999.99, "Electronics", 25, "Apple"));
        
        products.add(new Product(2L, "Samsung Galaxy", 
            "Premium Android phone with AI features", 899.99, "Electronics", 30, "Samsung"));
        
        products.add(new Product(3L, "MacBook", 
            "Thin and light laptop with M3 chip",1299.99, "Electronics", 15, "Apple"));
        
        products.add(new Product(4L, "Sony ", 
            " wireless headphones", 349.99, "Electronics", 50, "Sony"));
        
        products.add(new Product(5L, "iPad Pro", 
            "tablet with M2 chip",1099.99, "Electronics", 0, "Apple"));  // Out of stock
        
        // CLOTHING - 4 products
        products.add(new Product(6L, "Nike Air Max 270", 
            "Comfortable running shoes with air cushioning", 
            149.99, "Clothing", 100, "Nike"));
        
        products.add(new Product(7L, "Adidas Ultraboost", 
            "Premium running shoes with boost technology", 
            179.99, "Clothing", 75, "Adidas"));
        
        products.add(new Product(8L, "Levi's 501 Original Jeans", 
            "Classic straight fit jeans, iconic style", 
            69.99, "Clothing", 200, "Levi's"));
        
        products.add(new Product(9L, "North Face Jacket", 
            "Waterproof winter jacket with insulation", 
            249.99, "Clothing", 40, "The North Face"));
        
        // HOME & KITCHEN - 3 products
        products.add(new Product(10L, "Dyson V15 Vacuum", 
            "Cordless vacuum cleaner with laser detection", 
            649.99, "Home & Kitchen", 20, "Dyson"));
        
        products.add(new Product(11L, "Instant Pot Duo", 
            "7-in-1 electric pressure cooker, 6 quart", 
            89.99, "Home & Kitchen", 60, "Instant Pot"));
        
        products.add(new Product(12L, "KitchenAid Stand Mixer", 
            "Iconic stand mixer with 10 speeds and multiple attachments", 
            379.99, "Home & Kitchen", 0, "KitchenAid"));  // Out of stock
        
        // BOOKS - 2 products
        products.add(new Product(13L, "Atomic Habits", 
            "Best-selling book on building good habits by James Clear", 
            16.99, "Books", 500, "Penguin Random House"));
        
        products.add(new Product(14L, "The Psychology of Money", 
            "Timeless lessons on wealth and happiness by Morgan Housel", 
            14.99, "Books", 300, "Harriman House"));
        
        // SPORTS - 1 product
        products.add(new Product(15L, "Bowflex Dumbbells", 
            "Adjustable dumbbells, 5-52.5 lbs per dumbbell", 
            299.99, "Sports", 35, "Bowflex"));
        
    }
    
    
    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts(@RequestParam(required = false) Integer page, @RequestParam(required = false) Integer limit) {
        
        // If no pagination params, return all products
        if (page == null || limit == null) {
            return new ResponseEntity<>(products, HttpStatus.OK);
        }
        
        // Calculate pagination
        int startIndex = (page - 1) * limit;
        int endIndex = Math.min(startIndex + limit, products.size());
        
        // Validate page number
        if (startIndex >= products.size() || startIndex < 0) {
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK);
        }
        
        // Get paginated results
        List<Product> paginatedProducts = products.subList(startIndex, endIndex);
        
        
        return new ResponseEntity<>(paginatedProducts, HttpStatus.OK);
    }
    
    
    @GetMapping(value = "/{productId}")
    public ResponseEntity<Product> getProductById(@PathVariable Long productId) {
        
        Product foundProduct = null;
        for (Product product : products) {
            if (product.getProductId().equals(productId)) {
                foundProduct = product;
                break;
            }
        }
        
        if (foundProduct != null) {
            return new ResponseEntity<>(foundProduct, HttpStatus.OK);
        } else {
             return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    
    @GetMapping(value = "/category/{category}")
    public ResponseEntity<List<Product>> getProductsByCategory(@PathVariable String category) {
        
        List<Product> categoryProducts = new ArrayList<>();
        
        for (Product product : products) {
            if (product.getCategory().equalsIgnoreCase(category)) {
                categoryProducts.add(product);
            }
        }
        
        return new ResponseEntity<>(categoryProducts, HttpStatus.OK);
    }
    
    
    
    @GetMapping(value = "/brand/{brand}")
    public ResponseEntity<List<Product>> getProductsByBrand(@PathVariable String brand) {
        
        List<Product> brandProducts = new ArrayList<>();
        
        for (Product product : products) {
            if (product.getBrand().equalsIgnoreCase(brand)) {
                brandProducts.add(product);
            }
        }
        
       return new ResponseEntity<>(brandProducts, HttpStatus.OK);
    }
    
    
   
    @GetMapping(value = "/search")
    public ResponseEntity<List<Product>> searchProducts(@RequestParam String keyword) {
       
        List<Product> searchResults = new ArrayList<>();
        String lowerKeyword = keyword.toLowerCase();
        
        for (Product product : products) {
            // Search in both name and description
            boolean nameMatch = product.getName().toLowerCase().contains(lowerKeyword);
            boolean descMatch = product.getDescription().toLowerCase().contains(lowerKeyword);
            
            if (nameMatch || descMatch) {
                searchResults.add(product);
            }
        }
        
        return new ResponseEntity<>(searchResults, HttpStatus.OK);
    }
    
    
    @GetMapping(value = "/price-range")
    public ResponseEntity<List<Product>> getProductsByPriceRange(@RequestParam Double min,
            @RequestParam Double max) {
        
        List<Product> priceRangeProducts = new ArrayList<>();
        
        for (Product product : products) {
            if (product.getPrice() >= min && product.getPrice() <= max) {
                priceRangeProducts.add(product);
            }
        }
        
        return new ResponseEntity<>(priceRangeProducts, HttpStatus.OK);
    }
    
    
    
    @GetMapping(value = "/in-stock")
    public ResponseEntity<List<Product>> getInStockProducts() {
       
        List<Product> inStockProducts = new ArrayList<>();
        
        for (Product product : products) {
            if (product.getStockQuantity() > 0) {
                inStockProducts.add(product);
            }
        }
        
       return new ResponseEntity<>(inStockProducts, HttpStatus.OK);
    }
    
    
    @PostMapping
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        
        // Auto-generate product ID
        Long newId;
        if (products.isEmpty()) {
            newId = 1L;
        } else {
            newId = products.get(products.size() - 1).getProductId() + 1;
        }
        product.setProductId(newId);
        
        // Add to catalog
        products.add(product);
        
        return new ResponseEntity<>(product, HttpStatus.CREATED);  // 201 CREATED
    }
    
    
    @PutMapping(value = "/{productId}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long productId,@RequestBody Product updatedProduct) {
        
        System.out.println("Attempting to update product with ID: " + productId);
        
        Product existingProduct = null;
        int index = -1;
        
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getProductId().equals(productId)) {
                existingProduct = products.get(i);
                index = i;
                break;
            }
        }
        
        if (existingProduct != null) {
            
            // Keep same ID, update all other fields
            updatedProduct.setProductId(productId);
            products.set(index, updatedProduct);
            
            return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
        } else {
            System.out.println("Product not found with ID: " + productId);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    
    @PatchMapping(value = "/{productId}/stock")
    public ResponseEntity<Product> updateStock(@PathVariable Long productId,@RequestParam int quantity) {
        
        System.out.println("Updating stock for product ID: " + productId + " to " + quantity);
        
        Product foundProduct = null;
        for (Product product : products) {
            if (product.getProductId().equals(productId)) {
                foundProduct = product;
                break;
            }
        }
        
        if (foundProduct != null) {
            int oldStock = foundProduct.getStockQuantity();
            foundProduct.setStockQuantity(quantity);
            
            System.out.println("Stock updated: " + oldStock + " → " + quantity);
            System.out.println(foundProduct.getName() + " now has " + quantity + " units");
            
            return new ResponseEntity<>(foundProduct, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    
    
    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long productId) {
        
        boolean removed = false;
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getProductId().equals(productId)) {
                String productName = products.get(i).getName();
                products.remove(i);
                removed = true;
                System.out.println("Deleted: " + productName);
                break;
            }
        }
        
        if (removed) {
           return new ResponseEntity<>(HttpStatus.NO_CONTENT);  // 204 NO CONTENT
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);  // 404 NOT FOUND
        }
    }
}
