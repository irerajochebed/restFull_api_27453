package auca.ac.rw.UserProfileAPI.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import auca.ac.rw.UserProfileAPI.modal.UserProfile;
import auca.ac.rw.UserProfileAPI.response.ApiResponse;


@RestController // creating the controller class(USerProfileController)
@RequestMapping(value = "/api/user-profiles")
public class UserProfileController {
    
    // ArrayList that will store the userprofiles
     List<UserProfile> userProfiles = new ArrayList<>();
     Long idCounter = 1L;
    
    // CREATE the new user profile using the postMapping
    @PostMapping
    public ApiResponse<UserProfile> createUserProfile(@RequestBody UserProfile userProfile) {
        userProfile.setUserId(idCounter++);
        userProfile.setActive(true);
        userProfiles.add(userProfile);
        
        return new ApiResponse<>(
            true, 
            "User profile created successfully", 
            userProfile
        );
    }
    
    // READ - Get the user by ID
    @GetMapping(value = "/{userId}")
    public ApiResponse<UserProfile> getUserProfile(@PathVariable Long userId) {
        UserProfile found = userProfiles.stream()
            .filter(p -> p.getUserId().equals(userId))
            .findFirst()
            .orElse(null);
            
        if (found == null) {
            return new ApiResponse<>(false, "User not found", null);
        }
        
        return new ApiResponse<>(true, "User profile retrieved", found);
    }
    
    // READ - Get all user profile using the nottation of @GetMapping
    @GetMapping
    public ApiResponse<List<UserProfile>> getAllUserProfiles() {
        return new ApiResponse<>(
            true, 
            "Retrieved all user profiles", 
            userProfiles
        );
    }
    
    // UPDATE
    @PutMapping(value = "/{userId}")
    public ApiResponse<UserProfile> updateUserProfile(@PathVariable Long userId,@RequestBody UserProfile updatedProfile) {
        // Implementation for updating the user
        return new ApiResponse<>(true, "User profile updated", updatedProfile);
    }
    
    // DELETE
    @DeleteMapping(value = "/{userId}")
    public ApiResponse<Void> deleteUserProfile(@PathVariable Long userId) {
        // Implementation for deleting the user
        return new ApiResponse<>(true, "User profile deleted", null);
    }
    
    // SEARCH by username
    @GetMapping(value = "/search/username/{username}")
    public ApiResponse<List<UserProfile>> searchByUsername(@PathVariable String username) {
        // Implementation for searching users
        return new ApiResponse<>(true, "Search results", new ArrayList<>());
    }
    
    // SEARCH by country
    @GetMapping(value = "/search/country/{country}")
    public ApiResponse<List<UserProfile>> searchByCountry(@PathVariable String country) {
        // Implementation for searching users
        return new ApiResponse<>(true, "Search results", new ArrayList<>());
    }
    
    // SEARCH by age range
    @GetMapping(value = "/search/age-range")
    public ApiResponse<List<UserProfile>> searchByAgeRange(@RequestParam int minAge, @RequestParam int maxAge) {
        // Implementation for searching users
        return new ApiResponse<>(true, "Search results", new ArrayList<>());
    }
    
    // ACTIVATE
    @PutMapping(value = "/{userId}/activate")
    public ApiResponse<UserProfile> activateUserProfile(@PathVariable Long userId) {
        // Implementation for activating user
        return new ApiResponse<>(true, "User activated", null);
    }
    
    // DEACTIVATE
    @PutMapping(value = "/{userId}/deactivate")
    public ApiResponse<UserProfile> deactivateUserProfile(@PathVariable Long userId) {
        // Implementation for deactivating user
        return new ApiResponse<>(true, "User deactivated", null);
    }
}