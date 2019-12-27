package Marcel.myutil;

public class MapFromUserEntityToJson {
    public static String returnCredentialsInJson(String username, String password){
        StringBuilder result = new StringBuilder();
        result.append("{\n\t");
        result.append("\"username\":\"");
        result.append(username).append("\",\n\t");
        result.append("\"password\":\"").append(password).append("\"\n").append("}");
        return result.toString();
    }
}
