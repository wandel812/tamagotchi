package tamagotchi.view.animation.motion.settings;

import tamagotchi.DataLoader;
import tamagotchi.PropertiesAccessPoint;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class MotionAnimationSettings {
    private Map<String, MotionAnimationSetting> animationSettingMap;
    private static MotionAnimationSettings motionAnimationSettings;

    public MotionAnimationSettings() {
        animationSettingMap = new HashMap<>();
    }

    public static MotionAnimationSettings getAnimationSettingsInstance() {
        if (motionAnimationSettings == null) {
            try {
                motionAnimationSettings = new MotionAnimationSettings();
                motionAnimationSettings.initByJson(
                        PropertiesAccessPoint.applicationSettings.getProperty("motionAnimationSettingsPath")
                );
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return motionAnimationSettings;
    }

    public MotionAnimationSetting getMotionAnimationSetting(String motionName) {
        return animationSettingMap.get(motionName);
    }

    public void putMotionAnimationSetting(MotionAnimationSetting motionAnimationSetting) {
        animationSettingMap.put(
                motionAnimationSetting.getMotionName(),
                motionAnimationSetting
        );
    }

    private MotionAnimationSetting toMotionAnimationSetting(Map motionSett) {
        String motionName = (String) motionSett.get("motionName");
        Double duration = (Double) motionSett.get("durationInMillis");

        return new MotionAnimationSetting(motionName, duration);
    }

    public void initByJson(String path) throws IOException {
        Collection<Object> data = DataLoader.getDataFromJson(path).values();
        data.forEach(item -> putMotionAnimationSetting(toMotionAnimationSetting((Map) item)));
    }
}
