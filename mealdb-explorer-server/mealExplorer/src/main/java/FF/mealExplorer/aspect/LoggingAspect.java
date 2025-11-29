package FF.mealExplorer.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class LoggingAspect {

    /**
     * This method runs "Around" any method in the service package.
     * It logs Entry, Exit, and Execution Time.
     */
	
    @Around("execution(* FF.mealExplorer.service.*.*(..))")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        
        String className = joinPoint.getSignature().getDeclaringTypeName();
        String methodName = joinPoint.getSignature().getName();
        
        log.info(">> ENTERING: {}.{}() with args: {}", className, methodName, joinPoint.getArgs());
        Object result;
        try {
            result = joinPoint.proceed();
        } catch (Throwable e) {
            
            log.error("!! EXCEPTION in: {}.{}() message: {}", className, methodName, e.getMessage());
            throw e;
        }

        long end = System.currentTimeMillis();        
        log.info("<< EXITING: {}.{}() - Time taken: {} ms", className, methodName, (end - start));
        return result;
    }
}