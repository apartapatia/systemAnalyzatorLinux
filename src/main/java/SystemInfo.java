import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class SystemInfo {

    public static void main(String[] args) throws IOException {
        Map<String, Object> systemInfo = new HashMap<>();
        Map<String, Object> processInfo = new HashMap<>();
        getNumberOfDescriptors();

    }

    public static void getNumberOfDescriptors() {
        try {
            Process process = Runtime.getRuntime().exec("ls -l /proc/8812/fd/");
            try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                int descriptorCount = 0;
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    System.out.println(line);
                    descriptorCount++;
                }
                System.out.println("Number of descriptors: " + descriptorCount);
            }
            int exitCode = process.waitFor();
            if (exitCode == 0) {
                System.out.println("Command executed successfully.");
            } else {
                System.err.println("Command execution failed with exit code: " + exitCode);
            }
        } catch (IOException | InterruptedException e) {
            log.info(e.getMessage());
        }

    }
}
