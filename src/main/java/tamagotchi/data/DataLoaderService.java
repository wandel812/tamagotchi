package tamagotchi.data;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Properties;

public class DataLoaderService {
    public static Map getDataFromJson(String path) throws IOException, URISyntaxException {
        Path inputPath = Paths.get(DataLoaderService.class.getResource(path).toURI());
        Reader in = Files.newBufferedReader(inputPath);

        Gson gson = new Gson();
        Map data = gson.fromJson(new JsonReader(in), Map.class);
        in.close();
        return data;
    }

    public static Properties getDataFromProperties(InputStream is) throws IOException {
        Properties properties = new Properties();
        properties.load(is);
        is.close();
        return properties;
    }
}
