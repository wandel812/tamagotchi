package tamagotchi.data;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import org.springframework.core.io.ClassPathResource;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.*;
import java.util.Map;
import java.util.Properties;

public class DataLoaderService {
    public static Map getDataFromJson(String path) throws IOException, URISyntaxException {
        InputStream resource = new ClassPathResource(path).getInputStream();
        Map data = null;
        try (BufferedReader in = new BufferedReader(new InputStreamReader(resource))) {
            Gson gson = new Gson();
            data = gson.fromJson(new JsonReader(in), Map.class);
        }
        return data;
    }

    public static Properties getDataFromProperties(InputStream is) throws IOException {
        Properties properties = new Properties();
        properties.load(is);
        is.close();
        return properties;
    }
}
