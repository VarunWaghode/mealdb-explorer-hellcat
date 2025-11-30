package FF.mealExplorer.dto; 
import lombok.Data;
import java.io.Serializable;
import java.util.List;

@Data
public class AreaResponse implements Serializable {
    private List<Area> meals;

    @Data
    public static class Area implements Serializable {
        private String strArea;
    }
}