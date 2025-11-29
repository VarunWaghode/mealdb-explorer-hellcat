package FF.mealExplorer.dto; // Change package name if needed
import lombok.Data;
import java.io.Serializable;
import java.util.List;

@Data
public class AreaResponse implements Serializable {
    private List<Area> meals; // The API wraps the list in a "meals" key

    @Data
    public static class Area implements Serializable {
        private String strArea;
    }
}