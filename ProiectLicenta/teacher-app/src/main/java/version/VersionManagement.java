package version;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import entity.SubVersionEntity;
import utilitar.HttpRequestAPI;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.annotation.PostConstruct;
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
    private List<SubVersionEntity> versionEntities;
    private List<SubVersionEntity> all;

    @PostConstruct
    public void init(){
        this.versionEntities = new LinkedList<>();
        try {
            Map<String, Object> parameterValue = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();

            String string = parameterValue.get("projectId").toString();

            HttpResponse response = HttpRequestAPI.GETMethodResponse("http://localhost:9091/version/project/", string);

            SubVersionEntity[] versionEntities = this.gson.fromJson(response.body().toString(), SubVersionEntity[].class);

            List<SubVersionEntity> list = Arrays.stream(versionEntities)
                        .sorted(Comparator.comparing(SubVersionEntity::getProjectId))
                        .collect(Collectors.toList());

            String last = new UUID(0L, 0L).toString();
            for (SubVersionEntity s : list){
                if(!last.equals(s.getVersionId())){
                    last = s.getVersionId();
                    this.versionEntities.add(s);
                }
            }
            System.out.println("Ceva");
        } catch (IOException e) {
        } catch (InterruptedException ef) {
        } catch (NullPointerException | JsonSyntaxException nep){
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("http://localhost:8080/teacher-app/faces/xhtml/index.xhtml");
            } catch (IOException e) {
            }
        }
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
}
