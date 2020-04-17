package version;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import entity.SubVersionEntity;
import org.primefaces.PrimeFaces;
import org.w3c.dom.Text;
import utilitar.HttpRequestAPI;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.annotation.PostConstruct;
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
    private List<SubVersionEntity> versionEntities;
    private List<SubVersionEntity> all;
    private List<String> text;

    @PostConstruct
    public void init(){
        this.versionEntities = new LinkedList<>();
        this.all = new LinkedList<>();
        this.text = new LinkedList<>();

        try {
            Map<String, Object> parameterValue = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();

            String string = parameterValue.get("projectId").toString();

            HttpResponse response = HttpRequestAPI.GETMethodResponse("http://localhost:9091/version/project/", string);

            SubVersionEntity[] versionEntities = this.gson.fromJson(response.body().toString(), SubVersionEntity[].class);

            List<SubVersionEntity> list = Arrays.stream(versionEntities)
                        .sorted(Comparator.comparing(SubVersionEntity::getVersionId))
                        .collect(Collectors.toList());
            this.all.addAll(list);
            String last = new UUID(0L, 0L).toString();
            for (SubVersionEntity s : list){
                if(!last.equals(s.getVersionId())){
                    last = s.getVersionId();
                    this.versionEntities.add(s);
                }
            }

        } catch (InterruptedException | IOException | NullPointerException | IndexOutOfBoundsException | JsonSyntaxException nep){
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("http://localhost:8080/teacher-app/faces/xhtml/index.xhtml");
            } catch (IOException e) {
            }
        }
    }

    public String showVersions(SubVersionEntity entity){
        List<SubVersionEntity> list = this.all.stream()
                    .filter(a -> a.getVersionId().equals(entity.getVersionId()))
                    .collect(Collectors.toList());
        this.text = new LinkedList<>();

        Base64.Decoder d = Base64.getDecoder();
        for(SubVersionEntity s : list){
            this.text.add(new String(d.decode(s.getFile())));
        }

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

    public List<String> getText() {
        return text;
    }

    public String current(){
        return "versions";
    }
}
