package marcel.versionmanagement.models;


import marcel.versionmanagement.entities.person.StudentEntity;

public class SpecialGroupModel {
    private StudentEntity[] availableStudents;
    private StudentEntity[] groupStudents;

    public SpecialGroupModel(){
    }

    public SpecialGroupModel(StudentEntity[] availableStudents, StudentEntity[] groupStudents){
        this.availableStudents = availableStudents;
        this.groupStudents = groupStudents;
    }

    public StudentEntity[] getAvailableStudents() {
        return availableStudents;
    }

    public void setAvailableStudents(StudentEntity[] availableStudents) {
        this.availableStudents = availableStudents;
    }

    public StudentEntity[] getGroupStudents() {
        return groupStudents;
    }

    public void setGroupStudents(StudentEntity[] groupStudents) {
        this.groupStudents = groupStudents;
    }
}
