package FF.mealExplorer.controller;
/**
 * author Varun Waghode
 *
 *
 */
import FF.mealExplorer.dto.AreaResponse;
import FF.mealExplorer.dto.CategoryResponse;
import FF.mealExplorer.dto.IngredientResponse;
import FF.mealExplorer.dto.MealResponse;
import FF.mealExplorer.service.IMealService; 
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.cache.CacheManager;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@RestController
@RequestMapping("/api/v1/meals")
@RequiredArgsConstructor

public class MealController {

    private final IMealService service; 
    
    private final CacheManager cacheManager;

    @GetMapping("/search")
    public ResponseEntity<List<MealResponse.Meal>> search(@RequestParam @NotBlank(message = "Search query cannot be empty") 
    @Size(min = 2, message = "Search query must be at least 2 characters") 
    String query) {
        return ResponseEntity.ok(service.searchMeals(query));
    }

    @GetMapping("/categories")
    public ResponseEntity<List<CategoryResponse.Category>> getCategories() {
        return ResponseEntity.ok(service.getCategories());
    }

    @GetMapping("/random")
    public ResponseEntity<MealResponse.Meal> getRandom() {
        return ResponseEntity.ok(service.getRandomMeal());
    }
    
    @GetMapping("/areas")
    public ResponseEntity<List<AreaResponse.Area>> getAreas() {
        return ResponseEntity.ok(service.getAreas());
    }

    @GetMapping("/ingredients")
    public ResponseEntity<List<IngredientResponse .Ingredient>> getIngredients() {
        return ResponseEntity.ok(service.getIngredients());
    }

    @GetMapping("/filter")
    public ResponseEntity<List<MealResponse.Meal>> filter( @RequestParam(required = false) String category,
            @RequestParam(required = false) String area,
            @RequestParam(required = false) String ingredient) {
        
        if (area != null) return ResponseEntity.ok(service.filterByArea(area));
        if (ingredient != null) return ResponseEntity.ok(service.filterByIngredient(ingredient));
        if (category != null) return ResponseEntity.ok(service.filterByCategory(category));
        
        return ResponseEntity.badRequest().build();
    }
    
    @GetMapping("/cache/clear")
    public ResponseEntity<String> clearAllCaches() {
        cacheManager.getCacheNames()
            .forEach(cacheName -> {
                try {
                    cacheManager.getCache(cacheName).clear();
                } catch (Exception e) {
                    // Ignore 
                }
            });
        return ResponseEntity.ok("Redis Cache cleared successfully!");
    }
}