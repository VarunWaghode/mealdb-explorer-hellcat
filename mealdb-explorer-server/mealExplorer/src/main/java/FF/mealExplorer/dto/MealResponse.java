package FF.mealExplorer.dto;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import lombok.Data;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class MealResponse implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private List<Meal> meals;

    @Data
    public static class Meal implements Serializable {
        private String idMeal;
        private String strMeal;
        private String strCategory;
        private String strArea;
        private String strInstructions;
        private String strMealThumb;
        private String strYoutube;
        
        // This Map will catch strIngredient1, strMeasure1, etc.
        private Map<String, Object> extraDetails = new HashMap<>();

        @JsonAnySetter
        public void addDetail(String key, Object value) {
            extraDetails.put(key, value);
        }
    }
}