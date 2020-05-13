package entity;

import java.io.Serializable;
import java.util.UUID;

public class MaterialEntity implements Serializable {

    private UUID id;

    private String name;

    private int year;

    private String semester;

    public MaterialEntity(){
    }

    public MaterialEntity(UUID id, String name, int year, String semester){
        this.id = id;
        this.name = name;
        this.year = year;
        this.semester = semester;
    }

    @Override
    public String toString() {
        return "MaterialEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", year=" + year +
                ", semester='" + semester + '\'' +
                '}';
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }
}
