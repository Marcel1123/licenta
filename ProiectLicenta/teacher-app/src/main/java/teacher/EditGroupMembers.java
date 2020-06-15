package teacher;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import entity.GroupEntity;
import entity.person.StudentEntity;
import entity.person.TeacherEntity;
import models.AddMemberModel;
import models.SpecialGroupModel;
import org.primefaces.PrimeFaces;
import utilitar.HttpRequestAPI;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Map;

@ManagedBean
@RequestScoped
public class EditGroupMembers {
    private SpecialGroupModel specialGroupModel;
    private GroupEntity subject;
    private TeacherEntity teacher;
    private final Gson gson = new Gson();
    private int[] years = {1, 2, 3};

    private LinkedList<StudentEntity> availableStudents;
    private LinkedList<StudentEntity> groupStudents;

    private LinkedList<StudentEntity> filteredAvailableStudents;
    private LinkedList<StudentEntity> filteredGroupStudents;

    @PostConstruct
    public  void init(){
        specialGroupModel = new SpecialGroupModel();
        availableStudents = new LinkedList<>();
        groupStudents = new LinkedList<>();

        try {
            Map<String, Object> parameterValue = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
            String string = parameterValue.get("teacher").toString();
            this.teacher = this.gson.fromJson(string, TeacherEntity.class);

            string = parameterValue.get("target_group").toString();
            this.subject = this.gson.fromJson(string, GroupEntity.class);

            HttpResponse response = HttpRequestAPI.GETMethodResponse("http://localhost:9091/group/members/", subject.getId().toString());
            this.specialGroupModel = this.gson.fromJson(response.body().toString(), SpecialGroupModel.class);
            this.availableStudents = new LinkedList<StudentEntity>(Arrays.asList(this.specialGroupModel.getAvailableStudents()));
            this.groupStudents = new LinkedList<StudentEntity>(Arrays.asList(this.specialGroupModel.getGroupStudents()));
        } catch (InterruptedException | IOException | NullPointerException | IndexOutOfBoundsException | JsonSyntaxException nep){
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("http://localhost:8080/teacher-app/faces/xhtml/index.xhtml");
            } catch (IOException e) {
            }
        }
    }

    public void build(){
    }

    public String addMember(StudentEntity specialStudentModel1){
        try{
            AddMemberModel addMemberModel = new AddMemberModel(this.teacher.getId().toString(),
                    this.subject.getId().toString(),
                    specialStudentModel1.getPerson().getId().toString());

            HttpResponse response = HttpRequestAPI.POSTMethod("http://localhost:9091/group/members/add/", this.gson.toJson(addMemberModel));

            if(response.statusCode() == HttpURLConnection.HTTP_CREATED){
                this.availableStudents.remove(specialStudentModel1);
                this.groupStudents.add(specialStudentModel1);
            } else {
                PrimeFaces.current().executeScript("create_error_message(\"Add member\", \"\")");
            }
        } catch (InterruptedException | IOException | NullPointerException | IndexOutOfBoundsException | JsonSyntaxException nep){
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("http://localhost:8080/teacher-app/faces/xhtml/index.xhtml");
            } catch (IOException e) {
            }
        }
        specialStudentModel1 = null;;
        return "group-management";
    }

    public String removeMember(StudentEntity specialStudentModel){
        try{
            AddMemberModel addMemberModel = new AddMemberModel(this.teacher.getId().toString(),
                    this.subject.getId().toString(),
                    specialStudentModel.getPerson().getId().toString());

            HttpResponse response = HttpRequestAPI.POSTMethod("http://localhost:9091/group/members/remove/", this.gson.toJson(addMemberModel));

            if(response.statusCode() == HttpURLConnection.HTTP_NO_CONTENT){
                this.groupStudents.remove(specialStudentModel);
                this.availableStudents.add(specialStudentModel);
            } else {
                PrimeFaces.current().executeScript("create_error_message(\"Add member\", \"\")");
            }
        } catch (InterruptedException | IOException | NullPointerException | IndexOutOfBoundsException | JsonSyntaxException nep){
            return "index";
        }
        specialStudentModel = null;
        return "group-management";
    }

    public int getInteger(String string) {
        try {
            return Integer.valueOf(string);
        }
        catch (NumberFormatException ex) {
            return 0;
        }
    }

    public String backToHome(){
        return "groups";
    }

    public GroupEntity getSubject() {
        return subject;
    }

    public void setSubject(GroupEntity subject) {
        this.subject = subject;
    }

    public TeacherEntity getTeacher() {
        return teacher;
    }

    public void setTeacher(TeacherEntity teacher) {
        this.teacher = teacher;
    }

    public Gson getGson() {
        return gson;
    }

    public SpecialGroupModel getSpecialGroupModel() {
        return specialGroupModel;
    }

    public void setSpecialGroupModel(SpecialGroupModel specialGroupModel) {
        this.specialGroupModel = specialGroupModel;
    }

    public LinkedList<StudentEntity> getAvailableStudents() {
        return availableStudents;
    }

    public void setAvailableStudents(LinkedList<StudentEntity> availableStudents) {
        this.availableStudents = availableStudents;
    }

    public LinkedList<StudentEntity> getGroupStudents() {
        return groupStudents;
    }

    public void setGroupStudents(LinkedList<StudentEntity> groupStudents) {
        this.groupStudents = groupStudents;
    }

    public LinkedList<StudentEntity> getFilteredAvailableStudents() {
        return filteredAvailableStudents;
    }

    public void setFilteredAvailableStudents(LinkedList<StudentEntity> filteredAvailableStudents) {
        this.filteredAvailableStudents = filteredAvailableStudents;
    }

    public LinkedList<StudentEntity> getFilteredGroupStudents() {
        return filteredGroupStudents;
    }

    public void setFilteredGroupStudents(LinkedList<StudentEntity> filteredGroupStudents) {
        this.filteredGroupStudents = filteredGroupStudents;
    }

    public int[] getYears() {
        return years;
    }
}
