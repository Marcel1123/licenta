package marcel.versionmanagement.annotation;

import marcel.versionmanagement.exceptions.InvalidObjectsException;
import marcel.versionmanagement.exceptions.InvalidPathVariableException;
import marcel.versionmanagement.models.*;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.hibernate.mapping.Join;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import java.util.UUID;

@Aspect
@Component
public class ControllerAspects {

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

    @Pointcut("within(marcel.versionmanagement.controllers.*Controller)")
    public void controllers() {
    }

    @Pointcut("execution(* marcel.versionmanagement.controllers.ProjectController.addProject(..)))")
    public void addProject(){

    }

    @Pointcut("execution(* marcel.versionmanagement.controllers.ProjectController.getAllGroupProject(..)))")
    public void getAllGroupProject(){

    }

    @Pointcut("execution(* marcel.versionmanagement.controllers.VersionController.addVersion(..)))")
    public void addVersion(){

    }

    @Pointcut("execution(* marcel.versionmanagement.controllers.VersionController.getAllProjectVersion(..))")
    public void getAllProjectVersion(){

    }

    @Pointcut("execution(* marcel.versionmanagement.controllers.VersionController.getById(..))")
    public void getById(){

    }

    @Pointcut("execution(* marcel.versionmanagement.controllers.ProjectController.getUnfinishedProject(..))")
    public void getUnfinished(){

    }

    @Pointcut("publicMethods()&&controllers()")
    public void publicMethodsWithinControllers() {
    }

    @Before("addProject()")
    public void beforeAddProject(JoinPoint joinPoint) throws InvalidPathVariableException, InvalidObjectsException {
        Object[] objects = joinPoint.getArgs();
        verifyOneArgument(objects);
        try{
            CreateProjectModel createProjectModel = (CreateProjectModel)objects[0];
            verifyUUIDString(createProjectModel.getGroupId());
            verifyUUIDString(createProjectModel.getStudentId());
            if(createProjectModel.getName() == null || createProjectModel.getName().isEmpty()){
                throw new InvalidObjectsException("Invalid project name");
            }
            if(createProjectModel.getMaterialId() == null){
                throw new InvalidPathVariableException("Invalid UUID");
            }
            try{
                UUID uuid1 = UUID.fromString(createProjectModel.getMaterialId());
            }catch (IllegalArgumentException ile){
                throw new InvalidObjectsException("Invalid UUID");
            }
        } catch (NullPointerException npe){
            throw new InvalidObjectsException("Invalid object");
        }
    }

    @Before("getAllGroupProject()")
    public void beforeGetAllGroupProject(JoinPoint joinPoint) throws InvalidPathVariableException, InvalidObjectsException {
        Object[] objects = joinPoint.getArgs();
        verifyOneArgument(objects);
        String id = String.valueOf(objects[0]);
        verifyUUIDString(id);
    }

    @Before("addVersion()")
    public void beforeAddVersion(JoinPoint joinPoint) throws InvalidPathVariableException, InvalidObjectsException {
        Object[] objects = joinPoint.getArgs();
        verifyOneArgument(objects);
        try{
            VersionModel versionModel = (VersionModel)objects[0];
            validateUUIDNormal(versionModel.getProjectId());
            if(!versionModel.getIsFinal().equals("true") && !versionModel.getIsFinal().equals("false")){
                throw new InvalidObjectsException("Invalid IsFinal attribute");
            }
            for(ContentModel cm : versionModel.getCodeFile()){
                if(cm.getCode() == null || cm.getfName() == null
                    || cm.getfName().isEmpty() || cm.getCode().isEmpty()){
                    throw new InvalidObjectsException("Invalid version content");
                }
            }
        } catch (NullPointerException npe){
            throw new InvalidObjectsException("Invalid version Object");
        }
    }

    @Before("getAllProjectVersion()")
    public void beforeGetAllProjectVersion(JoinPoint joinPoint) throws InvalidPathVariableException, InvalidObjectsException {
        Object[] objects = joinPoint.getArgs();
        verifyOneArgument(objects);
        UUID id = UUID.fromString(String.valueOf(objects[0]));
        validateUUIDNormal(id);
    }

    @Before("getById()")
    public void beforeGetById(JoinPoint joinPoint) throws InvalidPathVariableException, InvalidObjectsException {
        Object[] objects = joinPoint.getArgs();
        verifyOneArgument(objects);
        String id = String.valueOf(objects[0]);
        verifyUUIDString(id);
    }

    @Before("getUnfinished()")
    public void beforeGetUnfinished(JoinPoint joinPoint) throws InvalidPathVariableException, InvalidObjectsException {
        Object[] objects = joinPoint.getArgs();
        verifyOneArgument(objects);
        String id = String.valueOf(objects[0]);
        verifyUUIDString(id);
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
