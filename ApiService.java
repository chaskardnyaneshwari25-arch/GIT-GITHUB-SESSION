import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class ApiService {
    public static double getScore(String resumeText, String jobDesc) {
        try {
            URL url = new URL("http://localhost:5000/analyze");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json");
            con.setDoOutput(true);

            String jsonInput = String.format("{\"resume\": \"%s\", \"job\": \"%s\"}",
                    resumeText.replace("\"", "'"), jobDesc.replace("\"", "'"));

            try (OutputStream os = con.getOutputStream()) {
                byte[] input = jsonInput.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            Scanner scanner = new Scanner(con.getInputStream());
            String response = scanner.useDelimiter("\\A").next();
            scanner.close();

            // Example response: {"score":0.82}
            return Double.parseDouble(response.replaceAll("[^0-9.]", ""));
        } catch (Exception e) {
            e.printStackTrace();
            return 0.0;
        }
    }
}
