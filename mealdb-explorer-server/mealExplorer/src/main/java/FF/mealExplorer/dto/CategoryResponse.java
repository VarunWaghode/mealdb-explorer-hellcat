package FF.mealExplorer.dto;

import lombok.Data;
import java.io.Serializable;
import java.util.List;

@Data
public class CategoryResponse implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private List<Category> categories;

    @Data
    public static class Category implements Serializable {
        private String idCategory;
        private String strCategory;
        private String strCategoryThumb;
    }
}