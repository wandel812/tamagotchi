package tamagotchi.controller.containers;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import tamagotchi.data.PropertiesAccessPoint;
import tamagotchi.model.meal.Meal;
import tamagotchi.model.meal.MealView;
import tamagotchi.model.pet.Pet;
import tamagotchi.model.pet.PetView;
import tamagotchi.model.pet.settings.PetSettings;
import tamagotchi.view.animation.sprite.SpriteAnimation;
import tamagotchi.view.animation.sprite.settings.SpriteAnimationSettings;

public class ModelContainer {
    private static Pet pet;
    private static PetView petView;
    private static Meal meal;
    private static MealView mealView;

    private ModelContainer() {
    }

    private static void checkInitialization() {
        if (pet == null || petView == null || meal == null || mealView == null) {
            throw new RuntimeException("EntityContainer is not initialized");
        }
    }

    public static Pet getPetInstance() {
        checkInitialization();
        return pet;
    }

    public static PetView getPetViewInstance() {
        checkInitialization();
        return petView;
    }

    public static Meal getMealInstance() {
        checkInitialization();
        return meal;
    }

    public static MealView getMealViewInstance() {
        checkInitialization();
        return mealView;
    }

    public static void init(String petName) {
        pet = new Pet(
                petName,
                Integer.parseInt(PropertiesAccessPoint.petBehaviorSettings.getProperty("birthCommunication")),
                Integer.parseInt(PropertiesAccessPoint.petBehaviorSettings.getProperty("birthTiredness")),
                Integer.parseInt(PropertiesAccessPoint.petBehaviorSettings.getProperty("birthHungriness"))
        );
        meal = new Meal(pet.getMealType());
        initPetView();
        initMealView();
    }

    private static void initPetView() {
        if (petView == null) {
            petView = new PetView(
                    new SpriteAnimation(
                            getCharacterTexturePath(),
                            SpriteAnimationSettings.getAnimationSettingsInstance()
                                    .getSpriteAnimationSetting("walk")),
                    new Point2D(
                            Integer.parseInt(PropertiesAccessPoint.applicationSettings.getProperty("width")) / 2.0,
                            Integer.parseInt(PropertiesAccessPoint.applicationSettings.getProperty("height")) / 2.0));
        } else {
            petView.getPetAnimation().setOtherAnimation(SpriteAnimationSettings.getAnimationSettingsInstance()
                    .getSpriteAnimationSetting("walk"));
            petView.getPetAnimation().getTexture().setImage(new Image(getCharacterTexturePath()));
        }
    }

    private static void initMealView() {
        Point2D initMealPosition = new Point2D(
                Double.parseDouble(
                        PropertiesAccessPoint.applicationSettings.getProperty("widthMargin")) / 2.0,
                Double.parseDouble(
                        PropertiesAccessPoint.applicationSettings.getProperty("height")) / 2.0);

        if (mealView == null) {
            mealView = new MealView(
                    new SpriteAnimation(
                            ModelContainer.getMealTexturePath(),
                            SpriteAnimationSettings.getAnimationSettingsInstance().getSpriteAnimationSetting("blank")),
                    initMealPosition);
        } else {
            mealView.getMealAnimation().getTexture().setImage(new Image(ModelContainer.getMealTexturePath()));
            mealView.getMealAnimation().setOtherAnimation(SpriteAnimationSettings.getAnimationSettingsInstance()
                    .getSpriteAnimationSetting("blank"));
            mealView.setMealPosition(initMealPosition);
        }
    }

    public static String getCharacterTexturePath() {
        return pet.isAdult() ? PetSettings.getPetSettingsInstance().getPetSetting(pet.getName()).getAdultTexturePath()
                : PetSettings.getPetSettingsInstance().getPetSetting(pet.getName()).getYoungTexturePath();
    }

    public static void changeCharacterTextureRegardingAge() {
        petView.getPetAnimation().getTexture().setImage(new Image(getCharacterTexturePath()));
    }

    public static String getMealTexturePath() {
        return PetSettings.getPetSettingsInstance().getPetSetting(pet.getName()).getMealTexturePath();
    }
}
