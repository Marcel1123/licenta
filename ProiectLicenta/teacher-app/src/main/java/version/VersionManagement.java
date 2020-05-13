package version;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import entity.SubVersionEntity;
import utilitar.HttpRequestAPI;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;
import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.*;
import java.util.stream.Collectors;

@ManagedBean
@RequestScoped
public class VersionManagement {
    private final Gson gson = new Gson();
    private List<String> text;
    private List<SubVersionEntity> versionEntities;
    private List<SubVersionEntity> all;

    @PostConstruct
    public void init(){
        this.versionEntities = new LinkedList<>();
        this.all = new LinkedList<>();
        this.text = new LinkedList<>();

        try {
            Map<String, Object> parameterValue = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();

            String string = parameterValue.get("projectId").toString();

            HttpResponse response = HttpRequestAPI.GETMethodResponse("http://localhost:9093/version/project/", string);

            SubVersionEntity[] versionEntities = this.gson.fromJson(response.body().toString(), SubVersionEntity[].class);

            List<SubVersionEntity> list = Arrays.stream(versionEntities)
                        .sorted(Comparator.comparing(SubVersionEntity::getUploadDate))
                        .collect(Collectors.toList());
            this.versionEntities = list;
        } catch (InterruptedException | IOException | NullPointerException | IndexOutOfBoundsException | JsonSyntaxException nep){
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("http://localhost:8080/teacher-app/faces/xhtml/index.xhtml");
            } catch (IOException e) {
            }
        }
    }

    public String showVersions(SubVersionEntity entity){
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("codeId", entity.getId().toString());
        return "code";
    }

    private UIComponent getUIComponent(String id) {
        return FacesContext.getCurrentInstance().getViewRoot().findComponent(id) ;
    }

    public String back(){
        return "projects";
    }

    public void build(ComponentSystemEvent event){
    }

    public List<SubVersionEntity> getVersionEntities() {
        return versionEntities;
    }

    public void setVersionEntities(List<SubVersionEntity> versionEntities) {
        this.versionEntities = versionEntities;
    }

    public List<SubVersionEntity> getAll() {
        return all;
    }

    public void setAll(List<SubVersionEntity> all) {
        this.all = all;
    }

    public void setText(List<String> text) {
        this.text = text;
    }

    public List<String> getText() {
        return text;
    }

    public String current(){
        return "versions";
    }
}
