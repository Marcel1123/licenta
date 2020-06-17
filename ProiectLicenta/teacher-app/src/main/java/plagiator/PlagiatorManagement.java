package plagiator;

import com.google.gson.Gson;
import entity.GroupEntity;
import entity.ProjectEntity;
import utilitar.HttpRequestAPI;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.http.HttpResponse;
import java.util.*;

@ManagedBean
@RequestScoped
public class PlagiatorManagement {
    public Map<ProjectEntity, Boolean> projectEntities;
    public ProjectEntity[] selected;
    private Map<String, Object> sessionVar;
    private GroupEntity groupEntity;
    private Gson gson = new Gson();
    public boolean b;

    @PostConstruct
    public void init(){
        try{
            projectEntities = new HashMap<>();
            this.sessionVar = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
            this.groupEntity = this.gson.fromJson(this.sessionVar.get("target_group").toString(), GroupEntity.class);
            HttpResponse response = HttpRequestAPI.GETMethodResponse("http://localhost:9093/project/finished/", this.groupEntity.getId().toString());

            if (response.statusCode() == HttpURLConnection.HTTP_OK){
                ProjectEntity[] projectEntities1 = gson.fromJson(response.body().toString(), ProjectEntity[].class);
                this.selected = projectEntities1;
                for(ProjectEntity p : projectEntities1){
                    projectEntities.put(p, false);
                }
            } else {
                try {
                    FacesContext.getCurrentInstance().getExternalContext().redirect("http://localhost:8080/teacher-app/faces/xhtml/projects.xhtml");
                } catch (IOException e) {
                }
            }
        } catch (IOException | InterruptedException | NullPointerException npe){
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("http://localhost:8080/teacher-app/faces/xhtml/index.xhtml");
            } catch (IOException e) {
            }
        }
    }

    public String startTest(){
        List<ProjectEntity> projectId = new LinkedList<>();
        for(Map.Entry e : projectEntities.entrySet()){
            if((boolean)e.getValue()){
                ProjectEntity p = (ProjectEntity)e.getKey();
                projectId.add(p);
            }
        }
        try {
            System.out.println(gson.toJson(projectId.toArray(new ProjectEntity[0])));
            HttpResponse response = HttpRequestAPI.POSTMethod("http://localhost:9093/plagiary/", gson.toJson(projectId.toArray(new ProjectEntity[0])));
        } catch (IOException | InterruptedException e) {
//            e.printStackTrace();
        } finally {
            return "projects";
        }
    }

    public void setB(boolean b) {
        this.b = b;
    }

    public boolean isB() {
        return b;
    }

    public String back(){
        return "projects";
    }

    public void select(ProjectEntity projectEntity){
        if(projectEntity != null){
            String summary = b ? "Checked" : "Unchecked";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(summary));
            projectEntities.put(projectEntity, !projectEntities.get(projectEntity));
        }
    }

    public Map<ProjectEntity, Boolean> getProjectEntities() {
        return projectEntities;
    }

    public void setProjectEntities(Map<ProjectEntity, Boolean> projectEntities) {
        this.projectEntities = projectEntities;
    }

    public ProjectEntity[] getSelected() {
        return selected;
    }

    public void setSelected(ProjectEntity[] selected) {
        this.selected = selected;
    }

    public Map<String, Object> getSessionVar() {
        return sessionVar;
    }

    public void setSessionVar(Map<String, Object> sessionVar) {
        this.sessionVar = sessionVar;
    }

    public GroupEntity getGroupEntity() {
        return groupEntity;
    }

    public void setGroupEntity(GroupEntity groupEntity) {
        this.groupEntity = groupEntity;
    }

    public Gson getGson() {
        return gson;
    }

    public void setGson(Gson gson) {
        this.gson = gson;
    }
}
