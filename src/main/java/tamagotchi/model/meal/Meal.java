package tamagotchi.model.meal;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import tamagotchi.PropertiesAccessPoint;
import tamagotchi.view.animation.sprite.SpriteAnimation;
import tamagotchi.view.animation.sprite.settings.SpriteAnimationSettings;
import tamagotchi.model.pet.Pet;

public class Meal {
    private MealType mealType;

    public Meal(MealType mealType) {
        this.mealType = mealType;
    }

    public MealType getMealType() {
        return mealType;
    }

    public void setMealType(MealType mealType) {
        this.mealType = mealType;
    }
}
