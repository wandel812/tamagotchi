/*
import com.google.gson.Gson;
import com.google.gson.stream.JsonWriter;
import org.junit.*;
import tamagotchi.animation.sprite.settings.SpriteAnimationSetting;
import tamagotchi.animation.sprite.settings.SpriteAnimationSettings;

import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class SpriteAnimationSettingsTest {

    static final Path path = Paths.get("src/main/resources/initByJsonTest.json");

    @BeforeClass
    public static void beforeTest() {
        try {
            Files.deleteIfExists(path);
            Files.createFile(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @AfterClass
    public static void afterTest() {
        try {
            Files.deleteIfExists(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void initByJsonTest() {
        Map<String, SpriteAnimationSetting> settMap = new HashMap<>();
        List<String> settNames = new ArrayList<>(
                Arrays.asList("walkAround", "jump", "sleep", "die", "beHappy", "beSad",
                        "beAngry", "beHungry", "eat", "goToBetterPlace", "beTired"));
        for (int i = 0; i < settNames.size(); i++) {
            settMap.put(
                    settNames.get(i),
                    new SpriteAnimationSetting(
                            settNames.get(i),
                            10 + i,
                            20 + i,
                            30 + i,
                            40 + i,
                            50 + i,
                            1000 + i
                    )
            );
        }

        SpriteAnimationSettings tamagotchiSetts = new SpriteAnimationSettings();
        try {
            Gson gson = new Gson();
            Writer output = Files.newBufferedWriter(path);
            JsonWriter jsonWriter = gson.newJsonWriter(output);
            gson.toJson(settMap, Map.class, jsonWriter);
            jsonWriter.close();

            tamagotchiSetts.initByJson(path.toString());
        } catch (IOException e) {
            e.printStackTrace();
            Assert.fail();
        }

        settNames.forEach(name ->
                Assert.assertEquals(
                        settMap.get(settNames.get(0)),
                        tamagotchiSetts.getSpriteAnimationSetting(settNames.get(0))
                )
        );

    }
}
*/
