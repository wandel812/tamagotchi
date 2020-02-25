package tamagotchi.data;

import java.io.IOException;
import java.util.Properties;

public class PropertiesAccessPoint {
    public static final Properties applicationSettings;
    public static final Properties petBehaviorSettings;

    static {
        applicationSettings = PropertiesAccessPoint.initProp(
                "src/main/resources/settings/applicationSettings.properties");
        petBehaviorSettings = PropertiesAccessPoint.initProp(
                "src/main/resources/settings/petBehaviorSettings.properties");
    }

    private static Properties initProp(String path) {
        Properties properties = null;
        try {
            properties = DataLoaderService.getDataFromProperties(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }
}
