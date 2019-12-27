package Marcel.myutil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

public class ReadResponseFromRequest {
    public static String getLoginResponse(HttpURLConnection postConnection) throws IOException {
        InputStream inputStream = postConnection.getInputStream();
        BufferedReader input = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder response = new StringBuilder();
        String currentLine;
        while ((currentLine = input.readLine()) != null)
            response.append(currentLine);

        input.close();
        return response.toString();
    }
}
