package Marcel.models;

public class ContentModel {
    private String code;
    private String fName;

    public ContentModel(String code, String fName) {
        this.code = code;
        this.fName = fName;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }
}
