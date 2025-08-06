import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) throws Exception {
        String resumeText = ResumeParser.parsePDF("resumes/resume1.pdf");
        String jobDesc = new String(Files.readAllBytes(Paths.get("job_description.txt")));

        double score = ApiService.getScore(resumeText, jobDesc);
        System.out.println("Relevance Score: " + score);
    }
}
