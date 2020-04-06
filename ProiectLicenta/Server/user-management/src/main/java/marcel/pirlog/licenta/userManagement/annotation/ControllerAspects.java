package marcel.pirlog.licenta.userManagement.annotation;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
public class ControllerAspects {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Around("@annotation(Timed)")
    public Object measure(ProceedingJoinPoint joinPoint) throws Throwable {
        String taskName = joinPoint.getSignature().toLongString();
        StopWatch watch = new StopWatch();
        watch.start(taskName);
        try{
            return joinPoint.proceed();
        } finally {
            watch.stop();
            logger.info(watch.prettyPrint());
        }
    }
}
