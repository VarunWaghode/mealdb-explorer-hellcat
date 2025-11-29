package FF.mealExplorer.service;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker; // Import
import FF.mealExplorer.client.MealDBClient;
import FF.mealExplorer.dto.AreaResponse;
import FF.mealExplorer.dto.CategoryResponse;
import FF.mealExplorer.dto.IngredientResponse;
import FF.mealExplorer.dto.MealResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MealServiceImpl implements IMealService {

    private final MealDBClient mealClient;
    
    private static final String CACHE_MEALS = "meals";
    private static final String SERVICE_NAME = "mealService";

    @Override
    @Cacheable(value = CACHE_MEALS, key = "#name", unless = "#result == null || #result.isEmpty()")
    @CircuitBreaker(name = SERVICE_NAME, fallbackMethod = "searchFallback") // <--- Protection
    public List<MealResponse.Meal> searchMeals(String name) {
        // No logging needed here anymore! AOP handles it.
        MealResponse response = mealClient.searchMeals(name);
        if (response != null && response.getMeals() != null) {
            return response.getMeals();
        }
        return Collections.emptyList();
    }

    // --- FALLBACK METHOD ---
    // Runs when API is down or throwing errors
    public List<MealResponse.Meal> searchFallback(String name, Throwable t) {
        // Note: AOP will log this exception automatically
        // Return empty list so the Frontend doesn't crash
        return Collections.emptyList();
    }

    @Override
    @Cacheable(value = "categories")
    @CircuitBreaker(name = SERVICE_NAME, fallbackMethod = "categoriesFallback")
    public List<CategoryResponse.Category> getCategories() {
        CategoryResponse response = mealClient.getCategories();
        if (response != null) {
            return response.getCategories();
        }
        return Collections.emptyList();
    }

    public List<CategoryResponse.Category> categoriesFallback(Throwable t) {
        return Collections.emptyList();
    }

    @Override
    public MealResponse.Meal getRandomMeal() {
        // Random requests shouldn't be cached, but should be protected
        MealResponse response = mealClient.getRandomMeal();
        if (response != null && response.getMeals() != null && !response.getMeals().isEmpty()) {
            return response.getMeals().get(0);
        }
        return null;
    }
    
    @Override
    public List<AreaResponse.Area> getAreas() {
        AreaResponse response = mealClient.getAreaList();
        if (response != null) return response.getMeals();
        return Collections.emptyList();
    }
    
    @Override
    public List<IngredientResponse.Ingredient> getIngredients() {
        IngredientResponse response = mealClient.getIngredientList();
        if (response != null) return response.getMeals();
        return Collections.emptyList();
    }

    @Override
    public List<MealResponse.Meal> filterByArea(String area) {
        MealResponse response = mealClient.filterByArea(area);
        return (response != null && response.getMeals() != null) ? response.getMeals() : Collections.emptyList();
    }
    
    @Override
    public List<MealResponse.Meal> filterByIngredient(String ingredient) {
        MealResponse response = mealClient.filterByIngredient(ingredient);
        return (response != null && response.getMeals() != null) ? response.getMeals() : Collections.emptyList();
    }
    
    @Override
    public List<MealResponse.Meal> filterByCategory(String category) {
        MealResponse response = mealClient.filterByCategory(category);
        return (response != null && response.getMeals() != null) ? response.getMeals() : Collections.emptyList();
    }
}