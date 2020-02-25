package tamagotchi.data;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Properties;

public class DataLoaderService {
    public static Map getDataFromJson(String path) throws IOException {
        Path inputPath = Paths.get(path);
        Reader in = Files.newBufferedReader(inputPath);
        Gson gson = new Gson();
        Map data = gson.fromJson(new JsonReader(in), Map.class);
        in.close();
        return data;
    }

    public static Properties getDataFromProperties(String path) throws IOException {
        FileInputStream fis = new FileInputStream(path);
        Properties properties = new Properties();
        properties.load(fis);
        fis.close();
        return properties;
    }
}
