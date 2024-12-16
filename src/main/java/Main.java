import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.AppConfig;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {
    private static String filePath = "config.json";
    private static ObjectMapper mapper = new ObjectMapper();
    public static void main(String[] args) {
        try {
            // Read the JSON file into a byte array
            byte[] jsonData = Files.readAllBytes(Paths.get(ClassLoader.getSystemResource(filePath).toURI()));
            AppConfig appConfig = processConfig(jsonData);
            ParkingManager manager = new ParkingManager(appConfig);
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }

    private static AppConfig processConfig(byte[] data) {
        try {
            return mapper.readValue(data, new TypeReference<AppConfig>() {
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
