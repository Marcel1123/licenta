package Marcel.models;


public final class MaterialModel {
    
    private String id;
    private String name;
    private int year;
    private String semester;

    public MaterialModel(){

    }

    public MaterialModel(String id, String name, int year, String semester){
        this.id = id;
        this.name = name;
        this.year = year;
        this.semester = semester;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getYear() {
        return year;
    }

    public String getSemester() {
        return semester;
    }
}
