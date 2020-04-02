package utilitar;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class UIMessage {
    public static void create_SEVERRITY_Message(String summary, String detail){
        FacesMessage message = new FacesMessage();
        message.setSeverity(FacesMessage.SEVERITY_ERROR);
        message.setSummary(summary);
        message.setDetail(detail);
        FacesContext.getCurrentInstance().addMessage("mesaje", message);
    }

    public static void create_ERROR_Message(String summary, String detail){
        FacesMessage message = new FacesMessage();
        message.setSeverity(FacesMessage.SEVERITY_ERROR);
        message.setSummary(summary);
        message.setDetail(detail);
        FacesContext.getCurrentInstance().addMessage("mesaje", message);
    }

    public static void create_WARNING_Message(String summary, String detail){
        FacesMessage message = new FacesMessage();
        message.setSeverity(FacesMessage.SEVERITY_WARN);
        message.setSummary(summary);
        message.setDetail(detail);
        FacesContext.getCurrentInstance().addMessage("mesaje", message);
    }
}
