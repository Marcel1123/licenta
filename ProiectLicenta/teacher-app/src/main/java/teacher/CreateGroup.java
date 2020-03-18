package teacher;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.io.Serializable;

@ManagedBean(name = "createGroup")
@RequestScoped
public class CreateGroup implements Serializable {

    private String groupName;

    public CreateGroup(){

    }

    public CreateGroup(String groupName){
        this.groupName = groupName;
    }

    public String getGroupName() {
        return groupName;
    }

    public void createGroup(){

    }
}
