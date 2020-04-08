package project;

import com.google.gson.Gson;
import entity.GroupEntity;
import models.GeneralProjectInformationModel;
import utilitar.HttpRequestAPI;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.http.HttpResponse;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@ManagedBean
@RequestScoped
public class ProjectManagement {
    private final Gson gson = new Gson();
    private Map<String, Object> sessionVar;
    private List<GeneralProjectInformationModel> projects;
    private GroupEntity groupEntity;

    @PostConstruct
    public void init(){
        this.projects = new LinkedList<>();
        this.sessionVar = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
        this.groupEntity = this.gson.fromJson(this.sessionVar.get("target_group").toString(), GroupEntity.class);
        try{
            HttpResponse response = HttpRequestAPI.GETMethodResponse("http://localhost:9091/project/projects/", this.groupEntity.getId());

            if (response.statusCode() == HttpURLConnection.HTTP_OK){
                GeneralProjectInformationModel[] p = this.gson.fromJson(response.body().toString(), GeneralProjectInformationModel[].class);
                for ( GeneralProjectInformationModel g : p){
                    if(g.getIsFinal().equals("false")){
                        g.setCompilationStatus("impossible");
                        g.setStatusPlagiere("impossible");
                    }
                    this.projects.add(g);
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

    public List<GeneralProjectInformationModel> getProjects() {
        return projects;
    }

    public void setProjects(List<GeneralProjectInformationModel> projects) {
        this.projects = projects;
    }

    public GroupEntity getGroupEntity() {
        return groupEntity;
    }

    public void setGroupEntity(GroupEntity groupEntity) {
        this.groupEntity = groupEntity;
    }

    public String goToVersions(GeneralProjectInformationModel generalProjectInformationModel){
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("projectId", generalProjectInformationModel.getProjectId());
        return "versions";
    }
}
