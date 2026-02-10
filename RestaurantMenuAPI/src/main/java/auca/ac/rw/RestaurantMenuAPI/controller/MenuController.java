package auca.ac.rw.RestaurantMenuAPI.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import auca.ac.rw.RestaurantMenuAPI.modal.MenuItem;

@RestController
@RequestMapping("/api/menu")
public class MenuController {
    
    
    List<MenuItem> menuItems = new ArrayList<>();
    
    public MenuController() {
        // APPETIZERS (2 items)
        menuItems.add(new MenuItem(1L, "Caesar Salad", 
            "Fresh natural salad", 8.99, "Appetizer", true));
        
        menuItems.add(new MenuItem(2L, "Chicken Wings", 
            "buffalo wings served", 12.50, "Appetizer", true));
        
        // MAIN COURSES (4 items)
        menuItems.add(new MenuItem(3L, "Grilled Salmon", 
            "Atlantic salmon with lemon vegetables", 
            24.99, "Main Course", true));
        
        menuItems.add(new MenuItem(4L, "Ribeye Steak", 
            "ribeye steak with mashed potatoes and asparagus",32.99, "Main Course", true));
        
        menuItems.add(new MenuItem(5L, "Pasta Carbonara","Creamy pasta with bacon and parmesan cheese", 
            16.99, "Main Course", false));  // Temporarily unavailable
        
        menuItems.add(new MenuItem(6L, "Vegetarian Pizza", 
            "pizza with fresh vegetables", 14.99, "Main Course", true));
        
        // DESSERTS (2 items)
        menuItems.add(new MenuItem(7L, "Chocolate Cake", "Warm chocolate cake with vanilla ice cream", 
            7.99, "Dessert", true));
        
        menuItems.add(new MenuItem(8L, "Tiramisu", "Classic Italian dessert with coffee", 
            8.50, "Dessert", true));
        
        // BEVERAGES (2 items)
        menuItems.add(new MenuItem(9L, "Fresh Lemonade", "Homemade lemonade with mint", 
            4.50, "Beverage", true));
        
        menuItems.add(new MenuItem(10L, "Espresso", "Rich Italian espresso", 
            3.50, "Beverage", true));
        
         }
    
    
    @GetMapping
    public ResponseEntity<List<MenuItem>> getAllMenuItems() {
         return new ResponseEntity<>(menuItems, HttpStatus.OK);
    }
    
    @GetMapping(value = "/{id}")
    public ResponseEntity<MenuItem> getMenuItemById(@PathVariable Long id) {
        
        MenuItem foundItem = null;
        for (MenuItem item : menuItems) {
            if (item.getId().equals(id)) {
                foundItem = item;
                break;
            }
        }
        
        if (foundItem != null) {
             return new ResponseEntity<>(foundItem, HttpStatus.OK);
        } else {
             return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
   
    @GetMapping(value = "/category/{category}")
    public ResponseEntity<List<MenuItem>> getMenuItemsByCategory(@PathVariable String category) {
        
        List<MenuItem> categoryItems = new ArrayList<>();
        
        for (MenuItem item : menuItems) {
            if (item.getCategory().equalsIgnoreCase(category)) {
                categoryItems.add(item);
            }
        }
        
        return new ResponseEntity<>(categoryItems, HttpStatus.OK);
    }
 
    @GetMapping(value = "/available")
    public ResponseEntity<List<MenuItem>> getMenuItemsByAvailability(@RequestParam boolean available) {
        
        List<MenuItem> filteredItems = new ArrayList<>();
        
        for (MenuItem item : menuItems) {
            if (item.isAvailable() == available) {
                filteredItems.add(item);
            }
        }
        
        return new ResponseEntity<>(filteredItems, HttpStatus.OK);
    }
    
    @GetMapping(value = "/search")
    public ResponseEntity<List<MenuItem>> searchMenuItemsByName(@RequestParam String name) {
        
        List<MenuItem> matchingItems = new ArrayList<>();
        
        for (MenuItem item : menuItems) {
            if (item.getName().toLowerCase().contains(name.toLowerCase())) {
                matchingItems.add(item);
            }
        }
        
        return new ResponseEntity<>(matchingItems, HttpStatus.OK);
    }
   
    @PostMapping
    public ResponseEntity<MenuItem> addMenuItem(@RequestBody MenuItem menuItem) {
        
        // Auto-generate ID
        Long newId;
        if (menuItems.isEmpty()) {
            newId = 1L;
        } else {
            newId = menuItems.get(menuItems.size() - 1).getId() + 1;
        }
        menuItem.setId(newId);
        
        // Add to menu
        menuItems.add(menuItem);
        
         return new ResponseEntity<>(menuItem, HttpStatus.CREATED);  // 201 CREATED
    }
    
    
    @PutMapping(value = "/{id}/availability")
    public ResponseEntity<MenuItem> toggleItemAvailability(@PathVariable Long id) {
        
        MenuItem foundItem = null;
        for (MenuItem item : menuItems) {
            if (item.getId().equals(id)) {
                foundItem = item;
                break;
            }
        }
        
        if (foundItem != null) {
            // Toggle availability (flip the boolean)
            boolean oldStatus = foundItem.isAvailable();
            foundItem.setAvailable(!oldStatus);
            
        
            return new ResponseEntity<>(foundItem, HttpStatus.OK);
        } else {
             return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteMenuItem(@PathVariable Long id) {
        System.out.println("Attempting to delete menu item with ID: " + id);
        
        boolean removed = false;
        for (int i = 0; i < menuItems.size(); i++) {
            if (menuItems.get(i).getId().equals(id)) {
                String itemName = menuItems.get(i).getName();
                menuItems.remove(i);
                removed = true;
                System.out.println(" Deleted: " + itemName);
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
