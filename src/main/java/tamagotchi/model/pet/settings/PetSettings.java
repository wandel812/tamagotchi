package tamagotchi.model.pet.settings;

import tamagotchi.DataLoader;
import tamagotchi.PropertiesAccessPoint;
import tamagotchi.model.meal.MealType;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class PetSettings {
    private Map<String, PetSetting> petSettingMap;
    private static PetSettings petSettings;

    private PetSettings() {
        petSettingMap = new HashMap<>();
    }

    public static PetSettings getPetSettingsInstance() {
        if (petSettings == null) {
            try {
                petSettings = new PetSettings();
                petSettings.initByJson(
                        PropertiesAccessPoint.applicationSettings.getProperty("petCharacterSettingsPath")
                );
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return petSettings;
    }

    public PetSetting getPetSetting(String petName) {
        return petSettingMap.get(petName);
    }

    public void putPetSetting(PetSetting petSetting) {
        petSettingMap.put(
                petSetting.getPetName(),
                petSetting
        );
    }

    private PetSetting toPetSetting(Map petSett) {
        String petName = (String) petSett.get("petName");
        String mealName = (String) petSett.get("mealName");
        MealType mealType = MealType.valueOf(mealName.toUpperCase());
        String youngTexturePath = (String) petSett.get("youngTexturePath");
        String adultTexturePath = (String) petSett.get("adultTexturePath");
        String mealTexturePath = (String) petSett.get("mealTexturePath");

        return new PetSetting(petName, mealType, youngTexturePath, adultTexturePath, mealTexturePath);
    }

    public void initByJson(String path) throws IOException {
        Collection<Object> data = DataLoader.getDataFromJson(path).values();
        data.forEach(item -> putPetSetting(toPetSetting((Map) item)));
    }

    public String[] getPetNameList() {
        return petSettingMap.keySet().toArray(new String[0]);
    }
}
