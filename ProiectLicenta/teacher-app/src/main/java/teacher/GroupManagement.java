package teacher;

import com.google.gson.Gson;
import entity.GroupEntity;
import entity.person.TeacherEntity;
import models.CreateGroupModel;
import org.primefaces.PrimeFaces;
import utilitar.HttpRequestAPI;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.http.HttpResponse;
import java.util.*;
import java.util.stream.Collectors;

@ManagedBean
@RequestScoped
public class GroupManagement {

    private GroupEntity groupEntity;
    private List<GroupEntity> groupEntities;
    private TeacherEntity teacher;
    private final Gson gson = new Gson();

    @PostConstruct
    public void init(){
        try {
            groupEntities = new LinkedList<>();
            groupEntity = new GroupEntity();

            Map<String, Object> parameterValue = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
            String string = parameterValue.get("teacher").toString();

            this.teacher = this.gson.fromJson(string, TeacherEntity.class);
            HttpResponse httpResponse = null;

            httpResponse = HttpRequestAPI.GETMethodResponse("http://localhost:9091/group/teacher/", this.teacher.getId().toString());
            GroupEntity[] groupEntities = this.gson.fromJson(httpResponse.body().toString(), GroupEntity[].class);
            this.groupEntities.addAll(Arrays.asList(groupEntities));
        } catch (IOException e) {
        } catch (InterruptedException ef) {
        } catch (NullPointerException nep){
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("http://localhost:8080/teacher-app/faces/xhtml/index.xhtml");
            } catch (IOException e) {
            }
        }
    }

    public void build(ComponentSystemEvent event){
    }

    public void removeGroup(GroupEntity groupEntity){
        try {
            HttpResponse response = HttpRequestAPI.DELETEMethod("http://localhost:9091/group/", groupEntity.getId().toString(), this.teacher.getId().toString());

            if(response.statusCode() == HttpURLConnection.HTTP_NO_CONTENT){
                this.groupEntities.removeIf(g -> groupEntity.getId().equals(g.getId()));
                PrimeFaces.current().executeScript("create_success_message(\"Remove group\", \"Group successful deleted\")");
            }
        } catch (IOException | InterruptedException e) {
            PrimeFaces.current().executeScript("create_warning_message(\"Remove group\", \"Server error\")");
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
        GroupEntity g = groupEntities.stream()
                            .filter(a -> a.getName().equals(groupEntity.getName()))
                            .collect(Collectors.toList())
                            .get(0);
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("target_group", gson.toJson(g));
        return "group-management";
    }

    public String groupProject(GroupEntity groupEntity){

        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("target_group", gson.toJson(groupEntity));
        return "projects";
    }

    public GroupEntity getGroupEntity() {
        return groupEntity;
    }

    public List<GroupEntity> getGroupEntities() {
        return groupEntities;
    }


}
