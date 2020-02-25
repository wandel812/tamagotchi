package tamagotchi.data;

import java.io.IOException;
import java.net.URI;
import java.nio.file.FileSystem;
import java.nio.file.FileSystemNotFoundException;
import java.nio.file.FileSystems;
import java.util.HashMap;
import java.util.Map;

public class FileSystemForURI {
    public static FileSystem init(URI uri) throws IOException
    {
        try {
            return FileSystems.getFileSystem(uri);
        }
        catch(FileSystemNotFoundException e) {
            Map<String, String> env = new HashMap<>();
            env.put("create", "true");
            return FileSystems.newFileSystem(uri, env);
        } catch(IllegalArgumentException e) {
            return FileSystems.getDefault();
        }
    }
}
