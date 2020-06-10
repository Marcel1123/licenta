package marcel.pirlog.licenta.userManagement.annotation;

import marcel.pirlog.licenta.userManagement.exceptions.InvalidObjectsException;
import marcel.pirlog.licenta.userManagement.exceptions.InvalidPathVariableException;
import marcel.pirlog.licenta.userManagement.models.AddMemberModel;
import marcel.pirlog.licenta.userManagement.models.CreateGroupModel;
import marcel.pirlog.licenta.userManagement.models.LoginModel;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
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

    @Pointcut("within(marcel.pirlog.licenta.userManagement.controllers.*Controller)")
    public void loginController() {
    }

    @Pointcut("publicMethods()&&loginController()")
    public void publicMethodsWithinControllers() {
    }

    @Pointcut("execution(* marcel.pirlog.licenta.userManagement.controllers.LoginController.getStudentByCredential(..))")
    public void getStudents() {
    }

    @Pointcut("execution(* marcel.pirlog.licenta.userManagement.controllers.LoginController.getTeacherByCredential(..))")
    public void getTeachers() {
    }

    @Pointcut("execution(* marcel.pirlog.licenta.userManagement.controllers.GroupController.addMember(..))")
    public void memberAdd(){
    }

    @Pointcut("execution(* marcel.pirlog.licenta.userManagement.controllers.GroupController.removeMember(..))")
    public void memberRemove(){
    }

    @Pointcut("execution(* marcel.pirlog.licenta.userManagement.controllers.GroupController.deleteGroup(..))")
    public void deleteGroup(){

    }

    @Pointcut("execution(* marcel.pirlog.licenta.userManagement.controllers.GroupController.addGroup(..))")
    public void addGroup(){

    }

    @Pointcut("execution(* marcel.pirlog.licenta.userManagement.controllers.GroupController.getGroupByName(..))")
    public void getGroupByName(){

    }

    @Pointcut("execution(* marcel.pirlog.licenta.userManagement.controllers.GroupController.getAllStudents(..))")
    public void getAllStudents(){

    }

    @Before("getAllStudents()")
    public void beforeGetAllStudents(JoinPoint joinPoint) throws InvalidPathVariableException, InvalidObjectsException {
        Object[] objects = joinPoint.getArgs();
        if(objects == null || objects.length != 1){
            throw new InvalidPathVariableException("Request body invalid");
        }
        try {
            String s = String.valueOf(objects[0]);
            verifyUUIDString(s);
        } catch (NullPointerException npe){
            throw new InvalidPathVariableException("Invalid parameter");
        }
    }

    @Before("getGroupByName()")
    public void beforeGetGroupByName(JoinPoint joinPoint) throws InvalidPathVariableException {
        Object[] objects = joinPoint.getArgs();
        if(objects == null || objects.length != 1){
            throw new InvalidPathVariableException("Invalid name");
        }
        String s = String.valueOf(objects[0]);
        if(s == null || s.isEmpty()){
            throw new InvalidPathVariableException("Invalid name");
        }
    }

    @Before("getStudents()")
    public void beforeGetByCredential(JoinPoint joinPoint) throws InvalidPathVariableException, InvalidObjectsException {
        verifyLoginModel(joinPoint);
    }

    @Before("getTeachers()")
    public void beforeGetTeacherByCredential(JoinPoint joinPoint) throws InvalidObjectsException, InvalidPathVariableException {
        verifyLoginModel(joinPoint);
    }

    @Before("memberAdd()")
    public void beforeMemberAdd(JoinPoint joinPoint) throws InvalidPathVariableException, InvalidObjectsException {
        verifyMemberModel(joinPoint);
    }

    @Before("memberRemove()")
    public void beforeMemberRemove(JoinPoint joinPoint) throws InvalidObjectsException, InvalidPathVariableException {
        verifyMemberModel(joinPoint);
    }

    @Before("deleteGroup()")
    public void beforeDeleteGroup(JoinPoint joinPoint) throws InvalidPathVariableException, InvalidObjectsException {
        Object[] objects = joinPoint.getArgs();
        if(objects == null || objects.length != 2){
            throw new InvalidPathVariableException("Invalid request body");
        }
        verifyUUIDString(String.valueOf(objects[0]));
        verifyUUIDString(String.valueOf(objects[1]));
    }

    @Before("addGroup()")
    public void beforeAddGroup(JoinPoint joinPoint) throws InvalidObjectsException {
        Object[] objects = joinPoint.getArgs();
        if(objects == null || objects.length != 1){
            throw new InvalidObjectsException("Request body invalid");
        }
        try{
            CreateGroupModel createGroupModel = (CreateGroupModel)objects[0];
            if(createGroupModel.getName() == null || createGroupModel.getName().isEmpty()){
                throw new InvalidObjectsException("Group name is invalid");
            }
            if(createGroupModel.getTeacherId() == null || createGroupModel.getTeacherId().equals(new UUID(0L, 0L))){
                throw new InvalidObjectsException("Teacher id is invalid");
            }
        } catch (NullPointerException npe){
            throw new InvalidObjectsException("Request body invalid");
        }
    }

    private void verifyLoginModel(JoinPoint joinPoint) throws InvalidPathVariableException, InvalidObjectsException {
        logger.info("Start getByCredential()");
        Object[] objects = joinPoint.getArgs();
        if(objects == null || objects.length == 0){
            throw new InvalidPathVariableException("Request body is empty");
        }
        if(objects.length != 1){
            throw new InvalidPathVariableException("Too many arguments");
        }
        try{
            LoginModel loginModel = (LoginModel)objects[0];
            if(loginModel.username == null || loginModel.password == null
                    || loginModel.username.isEmpty() || loginModel.password.isEmpty()){
                throw new InvalidObjectsException("Invalid login model");
            }
        } catch (NullPointerException npe){
            throw new InvalidObjectsException("Invalid login model");
        }
    }

    private void verifyMemberModel(JoinPoint joinPoint) throws InvalidPathVariableException, InvalidObjectsException {
        Object[] objects = joinPoint.getArgs();
        if(objects == null || objects.length != 1){
            throw new InvalidPathVariableException("Request body is empty");
        }
        try{
            AddMemberModel memberModel = (AddMemberModel)objects[0];
            verifyUUIDString(memberModel.getGroupId());
            verifyUUIDString(memberModel.getStudentId());
            verifyUUIDString(memberModel.getTeacherId());
        } catch (NullPointerException npe){
            throw new InvalidObjectsException("Invalid member object");
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
