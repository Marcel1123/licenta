package first.screen;

import com.google.gson.Gson;
import models.LoginModel;
import org.primefaces.PrimeFaces;
import utilitar.HttpRequestAPI;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.http.HttpResponse;
import java.util.Iterator;
import java.util.Map;

@ManagedBean
@RequestScoped
public class Login implements Serializable {

    private String username;
    private String password;

    public Login(){

    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String loginListener() {
        Map<String,String> parameterValue = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        this.username = parameterValue.get("formular:username");
        this.password = parameterValue.get("formular:password");
        Gson gson = new Gson();

        try {
            HttpResponse response = HttpRequestAPI.POSTMethod("http://localhost:9091/login/teacher", gson.toJson(new LoginModel(this.username, this.password)));

            if(response.statusCode() == HttpURLConnection.HTTP_CREATED){
                String string = (String) response.body();
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("teacher", string);
                return "groups";
            } else {
                PrimeFaces.current().executeScript("alerta_error_user()");
                return "index";
            }
        } catch (IOException | InterruptedException e) {
            PrimeFaces.current().executeScript("alerta_error_server()");
            return "index";
        }
    }
}
