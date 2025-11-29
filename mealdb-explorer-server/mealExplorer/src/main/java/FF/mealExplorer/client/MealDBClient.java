package FF.mealExplorer.client;

import FF.mealExplorer.dto.AreaResponse;
import FF.mealExplorer.dto.CategoryResponse;
import FF.mealExplorer.dto.IngredientResponse;
import FF.mealExplorer.dto.MealResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient(name = "mealdb", url = "${mealdb.api.url}")
public interface MealDBClient {

    @GetMapping("/search.php")
    MealResponse searchMeals(@RequestParam("s") String name);

    @GetMapping("/random.php")
    MealResponse getRandomMeal();
    
    @GetMapping("/categories.php")
    CategoryResponse getCategories();
    
    @GetMapping("/list.php?a=list")
    AreaResponse getAreaList();

    @GetMapping("/list.php?i=list")
    IngredientResponse getIngredientList();

    @GetMapping("/filter.php")
    MealResponse filterByArea(@RequestParam("a") String area);
 
    @GetMapping("/filter.php")
    MealResponse filterByIngredient(@RequestParam("i") String ingredient);

    @GetMapping("/filter.php")
    MealResponse filterByCategory(@RequestParam("c") String category);
}