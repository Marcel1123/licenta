package marcel.pirlog.licenta.userManagement.models;

public class SpecialGroupModel {
    private SpecialStudentModel[] availableStudents;
    private SpecialStudentModel[] groupStudents;

    public SpecialGroupModel(){
    }

    public SpecialGroupModel(SpecialStudentModel[] availableStudents, SpecialStudentModel[] groupStudents) {
        this.availableStudents = availableStudents;
        this.groupStudents = groupStudents;
    }

    public SpecialStudentModel[] getAvailableStudents() {
        return availableStudents;
    }

    public SpecialStudentModel[] getGroupStudents() {
        return groupStudents;
    }

    public void setGroupStudents(SpecialStudentModel[] groupStudents) {
        this.groupStudents = groupStudents;
    }

    public void setAvailableStudents(SpecialStudentModel[] availableStudents) {
        this.availableStudents = availableStudents;
    }
}
