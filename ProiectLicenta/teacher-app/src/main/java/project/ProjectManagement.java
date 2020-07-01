package project;

import com.google.gson.Gson;
import entity.GroupEntity;
import entity.ProjectEntity;
import utilitar.HttpRequestAPI;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.http.HttpResponse;
import java.util.Map;

@ManagedBean
@RequestScoped
public class ProjectManagement {
    private final Gson gson = new Gson();
    private Map<String, Object> sessionVar;
    private ProjectEntity[] projects;
    private GroupEntity groupEntity;

    @PostConstruct
    public void init(){
        try{
            this.projects = new ProjectEntity[0];
            this.sessionVar = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
            this.groupEntity = this.gson.fromJson(this.sessionVar.get("target_group").toString(), GroupEntity.class);
            HttpResponse response = HttpRequestAPI.GETMethodResponse("http://localhost:9093/project/projects/", this.groupEntity.getId().toString());

            if (response.statusCode() == HttpURLConnection.HTTP_OK){
                projects = gson.fromJson(response.body().toString(), ProjectEntity[].class);
                for(ProjectEntity p : projects){
                    if(!p.getIsFinal().equals("false")){
                        p.setIsFinal("true");
                    }
                }
            }
        } catch (IOException | InterruptedException | NullPointerException npe){
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("http://localhost:8080/teacher-app/faces/xhtml/index.xhtml");
            } catch (IOException e) {
            }
        }
    }

    public void build(ComponentSystemEvent event){
    }

    public ProjectEntity[] getProjects() {
        return projects;
    }

    public void setProjects(ProjectEntity[] projects) {
        this.projects = projects;
    }

    public GroupEntity getGroupEntity() {
        return groupEntity;
    }

    public void setGroupEntity(GroupEntity groupEntity) {
        this.groupEntity = groupEntity;
    }

    public String plagiator(){
        return "plagiator";
    }

    public String goToVersions(ProjectEntity projectEntity){
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("projectId", projectEntity.getId());
        return "versions";
    }
}
