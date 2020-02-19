package Marcel.myutil;

import Marcel.models.CreateProjectModel;

public class MapFromUserEntityToJson {
    public static String returnCredentialsInJson(String username, String password){
        StringBuilder result = new StringBuilder();
        result.append("{\n\t");
        result.append("\"username\":\"");
        result.append(username).append("\",\n\t");
        result.append("\"password\":\"").append(password).append("\"\n").append("}");
        return result.toString();
    }

    public static String returnCreateProjectInJson(CreateProjectModel createProjectModel){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{\n\t");
        stringBuilder.append("\"materialId\":\"");
        stringBuilder.append(createProjectModel.getMaterialId()).append("\",\n\t");
        stringBuilder.append("\"studentId\":\"").append(createProjectModel.getStudentId()).append("\",\n\t");
        stringBuilder.append("\"name\":\"").append(createProjectModel.getName()).append("\",\n\t");
        stringBuilder.append("\"groupId\":\"").append(createProjectModel.getGroupId()).append("\"\n");
        stringBuilder.append("}");
        return stringBuilder.toString();
    }
}
