package tamagotchi.data.saving;

import tamagotchi.model.pet.Pet;

import java.io.Serializable;

public class AppStateDto implements Serializable {
    private static final long serialVersionUID = 2765517828696227429L;
    private Pet pet;
    private double mealPositionX;
    private double mealPositionY;
    private double petPositionX;
    private double petPositionY;
    private long growingUpDelayTimer;
    private boolean isGameRestarted ;
    private boolean isInCommandCycle;


    public AppStateDto(Pet pet,
                       double mealPositionX, double mealPositionY,
                       double petPositionX, double petPositionY,
                       boolean isGameRestarted, boolean isInCommandCycle,
                       long growingUpDelayTimer) {
        this.pet = pet;
        this.mealPositionX = mealPositionX;
        this.mealPositionY = mealPositionY;
        this.petPositionX = petPositionX;
        this.petPositionY = petPositionY;
        this.isGameRestarted = isGameRestarted;
        this.isInCommandCycle = isInCommandCycle;
        this.growingUpDelayTimer = growingUpDelayTimer;
    }

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }

    public double getMealPositionX() {
        return mealPositionX;
    }

    public void setMealPositionX(double mealPositionX) {
        this.mealPositionX = mealPositionX;
    }

    public double getMealPositionY() {
        return mealPositionY;
    }

    public void setMealPositionY(double mealPositionY) {
        this.mealPositionY = mealPositionY;
    }

    public double getPetPositionX() {
        return petPositionX;
    }

    public void setPetPositionX(double petPositionX) {
        this.petPositionX = petPositionX;
    }

    public double getPetPositionY() {
        return petPositionY;
    }

    public void setPetPositionY(double petPositionY) {
        this.petPositionY = petPositionY;
    }

    public long getGrowingUpDelayTimer() {
        return growingUpDelayTimer;
    }

    public void setGrowingUpDelayTimer(long growingUpDelayTimer) {
        this.growingUpDelayTimer = growingUpDelayTimer;
    }

    public boolean isGameRestarted() {
        return isGameRestarted;
    }

    public void setGameRestarted(boolean gameRestarted) {
        isGameRestarted = gameRestarted;
    }

    public boolean isInCommandCycle() {
        return isInCommandCycle;
    }

    public void setInCommandCycle(boolean inCommandCycle) {
        isInCommandCycle = inCommandCycle;
    }
}
