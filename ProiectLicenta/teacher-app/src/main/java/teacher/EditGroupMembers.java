package teacher;

import com.google.gson.Gson;
import entity.GroupEntity;
import entity.TeacherEntity;
import models.AddMemberModel;
import models.SpecialGroupModel;
import models.SpecialStudentModel;
import org.primefaces.PrimeFaces;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;
import org.primefaces.model.DualListModel;
import utilitar.HttpRequestAPI;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.*;
import java.util.stream.Collectors;

@ManagedBean
@RequestScoped
public class EditGroupMembers {
    private SpecialGroupModel specialGroupModel;
    private GroupEntity subject;
    private TeacherEntity teacher;
    private final Gson gson = new Gson();

    private LinkedList<SpecialStudentModel> availableStudents;
    private LinkedList<SpecialStudentModel> groupStudents;

    private LinkedList<SpecialStudentModel> filteredAvailableStudents;
    private LinkedList<SpecialStudentModel> filteredGroupStudents;

    @PostConstruct
    public  void init(){
        specialGroupModel = new SpecialGroupModel();
        availableStudents = new LinkedList<>();
        groupStudents = new LinkedList<>();

        Map<String, Object> parameterValue = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
        String string = parameterValue.get("teacher").toString();
        this.teacher = this.gson.fromJson(string, TeacherEntity.class);

        string = parameterValue.get("target_group").toString();
        this.subject = this.gson.fromJson(string, GroupEntity.class);

        try {
            HttpResponse response = HttpRequestAPI.GETMethodResponse("http://localhost:9091/group/members/", subject.getId());
            this.specialGroupModel = this.gson.fromJson(response.body().toString(), SpecialGroupModel.class);
            this.availableStudents = new LinkedList<SpecialStudentModel>(Arrays.asList(this.specialGroupModel.getAvailableStudents()));
            this.groupStudents = new LinkedList<SpecialStudentModel>(Arrays.asList(this.specialGroupModel.getGroupStudents()));
            this.filteredAvailableStudents = this.availableStudents;
            this.filteredGroupStudents = this.groupStudents;
            } catch (IOException | InterruptedException e) {
        }
    }

    public void build(){
    }

    public boolean globalFilterFunctionYear(Object value, Object filter, Locale locale) {
        String filterText = (filter == null) ? null : filter.toString().trim();
        if (filterText == null || filterText.equals("")) {
            return true;
        }
        return ((String)value.toString()).startsWith(filterText);
    }

    public boolean globalFilterFunctionFirstName(Object value, Object filter, Locale locale) {
        String filterText = (filter == null) ? null : filter.toString().trim();
        if (filterText == null || filterText.equals("")) {
            return true;
        }
        return ((String)value.toString()).startsWith(filterText);
    }

    public boolean globalFilterFunctionLastName(Object value, Object filter, Locale locale) {
        String filterText = (filter == null) ? null : filter.toString().trim();
        if (filterText == null || filterText.equals("")) {
            return true;
        }
        return ((String)value.toString()).startsWith(filterText);
    }

    public boolean newGlobalFilter(Object value, Object filter, Locale locale) {
        String filterText = (filter == null) ? null : filter.toString().trim();
        if (filterText == null || filterText.equals("")) {
            return true;
        }
        return ((String)value.toString()).startsWith(filterText);
    }

    public boolean newGlobalFilterFirstName(Object value, Object filter, Locale locale) {
        String filterText = (filter == null) ? null : filter.toString().trim();
        if (filterText == null || filterText.equals("")) {
            return true;
        }
        return ((String)value.toString()).startsWith(filterText);
    }

    public boolean newGlobalFilterLastName(Object value, Object filter, Locale locale) {
        String filterText = (filter == null) ? null : filter.toString().trim();
        if (filterText == null || filterText.equals("")) {
            return true;
        }
        return ((String)value.toString()).startsWith(filterText);
    }


    public void addMember(SpecialStudentModel specialStudentModel1){
        try{
            AddMemberModel addMemberModel = new AddMemberModel(this.teacher.getId().toString(), this.subject.getId(), specialStudentModel1.getId().toString());
            HttpResponse response = HttpRequestAPI.POSTMethod("http://localhost:9091/group/members/add/", this.gson.toJson(addMemberModel));

            if(response.statusCode() == HttpURLConnection.HTTP_CREATED){
                this.availableStudents.remove(specialStudentModel1);
                this.groupStudents.add(specialStudentModel1);
            } else {
                PrimeFaces.current().executeScript("create_error_message(\"Add member\", \"\")");
            }
        } catch (IOException | InterruptedException e){
        }
//        this.filteredAvailableStudents = this.availableStudents;
//        this.filteredGroupStudents = this.groupStudents;
    }

    public void removeMember(SpecialStudentModel specialStudentModel){
        try{
            AddMemberModel addMemberModel = new AddMemberModel(this.teacher.getId().toString(), this.subject.getId(), specialStudentModel.getId().toString());
            HttpResponse response = HttpRequestAPI.POSTMethod("http://localhost:9091/group/members/remove/", this.gson.toJson(addMemberModel));

            if(response.statusCode() == HttpURLConnection.HTTP_NO_CONTENT){
                this.groupStudents.remove(specialStudentModel);
                this.availableStudents.add(specialStudentModel);
            } else {
                PrimeFaces.current().executeScript("create_error_message(\"Add member\", \"\")");
            }
        } catch (IOException | InterruptedException e){
        }
//        this.filteredAvailableStudents = this.availableStudents;
//        this.filteredGroupStudents = this.groupStudents;
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

    public LinkedList<SpecialStudentModel> getAvailableStudents() {
        return availableStudents;
    }

    public void setAvailableStudents(LinkedList<SpecialStudentModel> availableStudents) {
        this.availableStudents = availableStudents;
    }

    public LinkedList<SpecialStudentModel> getGroupStudents() {
        return groupStudents;
    }

    public void setGroupStudents(LinkedList<SpecialStudentModel> groupStudents) {
        this.groupStudents = groupStudents;
    }

    public LinkedList<SpecialStudentModel> getFilteredAvailableStudents() {
        return filteredAvailableStudents;
    }

    public void setFilteredAvailableStudents(LinkedList<SpecialStudentModel> filteredAvailableStudents) {
        this.filteredAvailableStudents = filteredAvailableStudents;
    }

    public LinkedList<SpecialStudentModel> getFilteredGroupStudents() {
        return filteredGroupStudents;
    }

    public void setFilteredGroupStudents(LinkedList<SpecialStudentModel> filteredGroupStudents) {
        this.filteredGroupStudents = filteredGroupStudents;
    }
}
