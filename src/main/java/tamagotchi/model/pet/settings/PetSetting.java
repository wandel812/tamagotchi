package tamagotchi.model.pet.settings;

import tamagotchi.model.meal.MealType;

public class PetSetting {
    private String petName;
    private MealType mealType;
    private String youngTexturePath;
    private String adultTexturePath;
    private String mealTexturePath;

    public PetSetting(
            String petName,
            MealType mealType,
            String youngTexturePath,
            String adultTexturePath,
            String mealTexturePath
    ) {
        this.petName = petName;
        this.mealType = mealType;
        this.youngTexturePath = youngTexturePath;
        this.adultTexturePath = adultTexturePath;
        this.mealTexturePath = mealTexturePath;
    }

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public MealType getMealType() {
        return mealType;
    }

    public void setMealType(MealType mealType) {
        this.mealType = mealType;
    }

    public String getYoungTexturePath() {
        return youngTexturePath;
    }

    public void setYoungTexturePath(String youngTexturePath) {
        this.youngTexturePath = youngTexturePath;
    }

    public String getAdultTexturePath() {
        return adultTexturePath;
    }

    public void setAdultTexturePath(String adultTexturePath) {
        this.adultTexturePath = adultTexturePath;
    }

    public String getMealTexturePath() {
        return mealTexturePath;
    }

    public void setMealTexturePath(String mealTexturePath) {
        this.mealTexturePath = mealTexturePath;
    }
}
