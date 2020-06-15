package marcel.compiling.annotation;

import marcel.compiling.entity.person.TeacherEntity;
import marcel.compiling.exceptions.ForbiddenException;
import marcel.compiling.exceptions.InvalidObjectsException;
import marcel.compiling.exceptions.InvalidPathVariableException;
import marcel.compiling.more.CompilingModel;
import marcel.compiling.repository.CompilerRepository;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import java.util.UUID;

@Aspect
@Component
public class ControllerAspects {

    @Autowired
    private CompilerRepository repository;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Around("@annotation(Timed)")
    public Object thread(ProceedingJoinPoint joinPoint) throws Throwable {
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

    @Pointcut("execution(public * *(..))")
    public void publicMethods() {
    }

    @Pointcut("within(marcel.compiling.controllers.*Controller)")
    public void controllers() {
    }

    @Pointcut("publicMethods()&&controllers()")
    public void publicMethodsWithinControllers() {
    }

    @Pointcut("execution(* marcel.compiling.controllers.CompilerController.requestCompile(..))")
    public void requestCompile(){

    }

    @Before("requestCompile()")
    public void beforeRequestCompile(JoinPoint joinPoint) throws InvalidPathVariableException, InvalidObjectsException, ForbiddenException {
        Object[] objects = joinPoint.getArgs();
        verifyOneArgument(objects);
        try{
            CompilingModel compilingModels = (CompilingModel)objects[0];
            verifyUUIDString(compilingModels.getTeacherId());
            verifyUUIDString(compilingModels.getVersionId());
            TeacherEntity teacherEntity = repository.getTeacher(compilingModels.getTeacherId());
            if(teacherEntity == null){
                throw new ForbiddenException("No result in database");
            }
        } catch (NullPointerException npe){
            throw new InvalidObjectsException("Invalid object");
        }
    }

    private void verifyOneArgument(Object[] objects) throws InvalidPathVariableException {
        if(objects == null || objects.length != 1){
            throw new InvalidPathVariableException("Invalid request body");
        }
    }

    private void verifyUUIDString(String uuid) throws InvalidPathVariableException, InvalidObjectsException {
        if(uuid == null){
            throw new InvalidPathVariableException("Invalid UUID");
        }
        try{
            UUID uuid1 = UUID.fromString(uuid);
            if(uuid1.equals(new UUID(0L,0L))){
                throw new InvalidPathVariableException("UUID id empty");
            }
        }catch (IllegalArgumentException ile){
            throw new InvalidObjectsException("Invalid UUID");
        }
    }

    private void validateUUIDNormal(UUID uuid) throws InvalidObjectsException {
        if(uuid == null){
            throw new InvalidObjectsException("Invalid UUID");
        }
        if(uuid.equals(new UUID(0L, 0L))){
            throw new InvalidObjectsException("Invalid UUID");
        }
    }
}
