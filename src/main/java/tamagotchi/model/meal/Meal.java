package tamagotchi.model.meal;

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
