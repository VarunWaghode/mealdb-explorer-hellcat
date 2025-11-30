package FF.mealExplorer.component;

import FF.mealExplorer.service.IMealService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class CacheWarmer {

    private final IMealService mealService;

    // List of popular items we want always fast
    private final List<String> POPULAR_SEARCHES = Arrays.asList(
        "Chicken", "Beef", "Pasta", "Seafood", "Vegetarian"
    );

    /**
     *  Run when the App starts 
     *	5 seconds Delay by default
     * 
     */
    @EventListener(ApplicationReadyEvent.class)
    public void onStartup() {
        new Thread(() -> {
            try {
                log.info("Meal Explorer Started Started Waiting 5 seconds before warming cache...");
                Thread.sleep(5000); // Wait 5 seconds
                warmUpCache();
            } catch (InterruptedException e) {
                log.error("Cache warm-up interrupted", e);
                Thread.currentThread().interrupt();
            }
        }).start();
    }

    /**
     * Run every 30 minutes (fixedDelay in milliseconds).
     *  initialDelay = 900000 (15 mins): Wait 15 mins before the FIRST run.
     * 30 min = 1800000 ms
     */
    @Scheduled(fixedDelay = 1800000, initialDelay = 900000)
    public void scheduledWarmUp() {
        log.info("Scheduled Task: Refreshing cache...");
        warmUpCache();
    }

    private void warmUpCache() {
        long start = System.currentTimeMillis();
        
        // We assume filterByCategory caches the result.
        // We call it here so the result is stored in Redis.
        POPULAR_SEARCHES.forEach(category -> {
            log.info("Pre-fetching category: {}", category);
            try {
                mealService.filterByCategory(category);
            } catch (Exception e) {
                log.warn("Failed to warm up category: {}", category);
            }
        });

        long end = System.currentTimeMillis();
        log.info("Cache Warm-up finished in {} ms", (end - start));
    }
}