package FF.mealExplorer; 

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;
/**
 * 
 * @author varun Waghode
 *
 */
@SpringBootApplication
@EnableFeignClients  
@EnableScheduling
public class MealExplorerApplication {

	public static void main(String[] args) {
		SpringApplication.run(MealExplorerApplication.class, args);
	}

}