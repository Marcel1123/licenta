package version;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import entity.SubVersionContentEntity;
import utilitar.HttpRequestAPI;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.Base64;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@ManagedBean
@RequestScoped
public class ContentManagement {
    private final Gson gson = new Gson();
    private List<String> text;

    @PostConstruct
    public void init(){
        this.text = new LinkedList<>();

        try {
            Map<String, Object> parameterValue = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();

            String string = parameterValue.get("codeId").toString();

            HttpResponse response = HttpRequestAPI.GETMethodResponse("http://localhost:9093/version/", string);

            SubVersionContentEntity[] entity = gson.fromJson(response.body().toString(), SubVersionContentEntity[].class);

            Base64.Decoder d = Base64.getDecoder();
            for(SubVersionContentEntity s : entity){
                this.text.add(new String(d.decode(s.getFile())));
            }
        } catch (InterruptedException | IOException | NullPointerException | IndexOutOfBoundsException | JsonSyntaxException nep){
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("http://localhost:8080/teacher-app/faces/xhtml/index.xhtml");
            } catch (IOException e) {
            }
        }
    }

    public String back(){
        return "versions";
    }

    public List<String> getText() {
        return text;
    }

    public void setText(List<String> text) {
        this.text = text;
    }
}
