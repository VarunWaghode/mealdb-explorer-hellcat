package FF.mealExplorer.service;

import FF.mealExplorer.dto.AreaResponse;
import FF.mealExplorer.dto.CategoryResponse;
import FF.mealExplorer.dto.IngredientResponse;
import FF.mealExplorer.dto.MealResponse;
import java.util.List;

public interface IMealService {
	
    List<MealResponse.Meal> searchMeals(String name);
    
    List<CategoryResponse.Category> getCategories();
    
    MealResponse.Meal getRandomMeal();

    List<AreaResponse.Area> getAreas();
    
    List<IngredientResponse.Ingredient> getIngredients();
    
    List<MealResponse.Meal> filterByArea(String area);
    
    List<MealResponse.Meal> filterByIngredient(String ingredient);
    
    List<MealResponse.Meal> filterByCategory(String category);
}