package tamagotchi.data;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesAccessPoint {
    public static final Properties applicationSettings;
    public static final Properties petBehaviorSettings;

    static {
        applicationSettings = PropertiesAccessPoint.initProp(
                PropertiesAccessPoint.class.
                        getResourceAsStream("/settings/applicationSettings.properties")
        );
        petBehaviorSettings = PropertiesAccessPoint.initProp(
                PropertiesAccessPoint.class.
                        getResourceAsStream("/settings/petBehaviorSettings.properties")
        );
    }

    private static Properties initProp(InputStream is) {
        Properties properties = null;
        try {
            properties = DataLoaderService.getDataFromProperties(is);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }
}
