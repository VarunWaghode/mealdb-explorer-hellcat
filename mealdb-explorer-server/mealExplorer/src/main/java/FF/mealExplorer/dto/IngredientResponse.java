package FF.mealExplorer.dto;
import lombok.Data;
import java.io.Serializable;
import java.util.List;

@Data
public class IngredientResponse implements Serializable {
    private List<Ingredient> meals;

    @Data
    public static class Ingredient implements Serializable {
        private String idIngredient;
        private String strIngredient;
        private String strDescription;
    }
}