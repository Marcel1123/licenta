package teacher;

import com.google.gson.Gson;
import entity.GroupEntity;
import entity.TeacherEntity;
import models.CreateGroupModel;
import models.DeleteGroupModel;
import org.primefaces.PrimeFaces;
import utilitar.HttpRequestAPI;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;
import java.io.IOException;

import java.net.HttpURLConnection;
import java.net.http.HttpResponse;
import java.util.*;

@ManagedBean
@RequestScoped
public class GroupManagement {

    private GroupEntity groupEntity;
    private List<GroupEntity> groupEntities;
    private TeacherEntity teacher;
    private final Gson gson = new Gson();

    @PostConstruct
    public void init(){
        groupEntities = new LinkedList<>();
        groupEntity = new GroupEntity();

        Map<String, Object> parameterValue = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
        String string = parameterValue.get("teacher").toString();

        this.teacher = this.gson.fromJson(string, TeacherEntity.class);
        HttpResponse httpResponse = null;
        try {
            httpResponse = HttpRequestAPI.GETMethodResponse("http://localhost:9091/group/teacher/", this.teacher.getId().toString());
            GroupEntity[] groupEntities = this.gson.fromJson(httpResponse.body().toString(), GroupEntity[].class);
            this.groupEntities.addAll(Arrays.asList(groupEntities));
        } catch (IOException e) {
        } catch (InterruptedException ef) {
        }
    }

    public void build(ComponentSystemEvent event){
    }

    public void removeGroup(GroupEntity groupEntity){
        DeleteGroupModel deleteGroupModel = new DeleteGroupModel(groupEntity.getId(), this.teacher.getId().toString());
        try {
            HttpResponse response = HttpRequestAPI.DELETEMethod("http://localhost:9091/group/", groupEntity.getId(), this.teacher.getId().toString());

            if(response.statusCode() == HttpURLConnection.HTTP_NO_CONTENT){
                this.groupEntities.removeIf(g -> groupEntity.getId().equals(g.getId()));
                PrimeFaces.current().executeScript("create_success_message(\"Create group\", \"Group successful deleted\")");
            }
        } catch (IOException | InterruptedException e) {
            PrimeFaces.current().executeScript("create_warning_message(\"Create group\", \"Server error\")");
        }
    }

    public void createNewGroup(){
        if(this.groupEntity.getName() == null || this.groupEntity.getName().equals("")) {
            PrimeFaces.current().executeScript("create_error_message(\"Create group error\", \"Group name is mandatory!\")");
        } else {
            CreateGroupModel createGroupModel = new CreateGroupModel(this.groupEntity.getName(), UUID.fromString(this.teacher.getId().toString()));
            try {
                HttpResponse response = HttpRequestAPI.POSTMethod("http://localhost:9091/group/", this.gson.toJson(createGroupModel));

                if(response.statusCode() == HttpURLConnection.HTTP_CREATED){
                    this.groupEntities.add(gson.fromJson(response.body().toString(), GroupEntity.class));
                    PrimeFaces.current().executeScript("create_success_message(\"Create group\", \"Your successful created\")");
                } else if(response.statusCode() == HttpURLConnection.HTTP_BAD_REQUEST && response.body().toString().equals("Group already exist!")){
                    PrimeFaces.current().executeScript("create_error_message(\"Create group error\", \"Group already exist!\")");
                } else {
                    PrimeFaces.current().executeScript("create_warning_message(\"Create group\", \"Unknown error\")");
                }
            } catch (IOException | InterruptedException e) {
                PrimeFaces.current().executeScript("create_warning_message(\"Create group\", \"Server error\")");
            }
            this.groupEntity.setName("");
        }
    }

    public String groupInformation(GroupEntity groupEntity){
        try {
            HttpResponse response = HttpRequestAPI.GETMethodResponse("http://localhost:9091/group/name/", groupEntity.getName());
            GroupEntity groupEntity1 = gson.fromJson(response.body().toString(), GroupEntity.class);
            if(!groupEntity.getId().equals(groupEntity1.getId()) || !this.teacher.getId().toString().equals(groupEntity1.getCreatorId())){
                return "groups";
            } else {
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("target_group", response.body().toString());
                return "group-management";
            }
        } catch (IOException | InterruptedException e) {
            return "groups";
        }
    }

    public GroupEntity getGroupEntity() {
        return groupEntity;
    }

    public List<GroupEntity> getGroupEntities() {
        return groupEntities;
    }
}
