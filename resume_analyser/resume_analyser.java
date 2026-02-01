import java.io.*;
import java.util.*;

public class ResumeAnalyzer {

    static Set<String> extractSkills(String text) {
        String[] skillList = {
                "java", "python", "sql", "html", "css",
                "javascript", "react", "spring", "mysql"
        };

        Set<String> foundSkills = new HashSet<>();
        text = text.toLowerCase();

        for (String skill : skillList) {
            if (text.contains(skill)) {
                foundSkills.add(skill);
            }
        }
        return foundSkills;
    }

    static String readFile(String fileName) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        StringBuilder sb = new StringBuilder();
        String line;

        while ((line = br.readLine()) != null) {
            sb.append(line).append(" ");
        }
        br.close();
        return sb.toString();
    }

    public static void main(String[] args) {
        try {
            String resumeText = readFile("resume.txt");
            String jobText = readFile("job_description.txt");

            Set<String> resumeSkills = extractSkills(resumeText);
            Set<String> jobSkills = extractSkills(jobText);

            resumeSkills.retainAll(jobSkills);

            int matchPercentage = (resumeSkills.size() * 100) / jobSkills.size();

            System.out.println("Matched Skills: " + resumeSkills);
            System.out.println("Match Percentage: " + matchPercentage + "%");

        } catch (Exception e) {
            System.out.println("Error reading files.");
        }
    }
}
