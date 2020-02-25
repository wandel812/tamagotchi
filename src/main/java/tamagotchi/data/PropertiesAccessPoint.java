package tamagotchi.data;

import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.util.Properties;

public class PropertiesAccessPoint {
    public static final Properties applicationSettings;
    public static final Properties petBehaviorSettings;

    static {
        applicationSettings = PropertiesAccessPoint.initProp("/settings/applicationSettings.properties");
        petBehaviorSettings = PropertiesAccessPoint.initProp("/settings/petBehaviorSettings.properties");
    }

    private static Properties initProp(String path) {
        Properties properties = null;
        try {
            properties = DataLoaderService.getDataFromProperties(new ClassPathResource(path).getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;    }
}
