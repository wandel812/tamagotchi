package tamagotchi.view.animation.sprite.settings;

import tamagotchi.data.DataLoaderService;
import tamagotchi.data.PropertiesAccessPoint;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class SpriteAnimationSettings {
    private Map<String, SpriteAnimationSetting> animationSettingMap;
    private static SpriteAnimationSettings spriteAnimationSettings;

    public SpriteAnimationSettings() {
        animationSettingMap = new HashMap<>();
    }

    public static SpriteAnimationSettings getAnimationSettingsInstance() {
        if (spriteAnimationSettings == null) {
            try {
                spriteAnimationSettings = new SpriteAnimationSettings();
                spriteAnimationSettings.initByJson(
                        PropertiesAccessPoint.applicationSettings.getProperty("spriteAnimationSettingsPath")
                );
            } catch (IOException e) {
                e.printStackTrace();
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
        }
        return spriteAnimationSettings;
    }

    public SpriteAnimationSetting getSpriteAnimationSetting(String animationName) {
        return animationSettingMap.get(animationName);
    }

    public void putSpriteAnimationSetting(SpriteAnimationSetting spriteAnimationSetting) {
        animationSettingMap.put(
                spriteAnimationSetting.getAnimationName(),
                spriteAnimationSetting
        );
    }

    private SpriteAnimationSetting toSpriteAnimationSetting(Map tamagotchiSett) {
        String animationName = (String) tamagotchiSett.get("animationName");
        Double count = (Double) tamagotchiSett.get("count");
        Double offsetX = (Double) tamagotchiSett.get("offsetX");
        Double offsetY = (Double) tamagotchiSett.get("offsetY");
        Double width = (Double) tamagotchiSett.get("width");
        Double height = (Double) tamagotchiSett.get("height");
        Double duration = (Double) tamagotchiSett.get("durationInMillis");

        return new SpriteAnimationSetting(animationName, count.intValue(), offsetX.intValue(),
                offsetY.intValue(), width.intValue(), height.intValue(), duration);
    }

    public void initByJson(String path) throws IOException, URISyntaxException {
        Collection<Object> data = DataLoaderService.getDataFromJson(path).values();
        data.forEach(item -> putSpriteAnimationSetting(toSpriteAnimationSetting((Map) item)));
    }
}