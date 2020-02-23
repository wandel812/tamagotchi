package tamagotchi.model.pet;

import tamagotchi.PropertiesAccessPoint;
import tamagotchi.model.meal.MealType;
import tamagotchi.model.pet.settings.PetSettings;

public class Pet {
    private String name;
    private int communication;
    private int tiredness;
    private int hungriness;
    private boolean isAdult;
    private boolean isDead;

    public Pet(String name, int communication, int tiredness, int hungriness) {
        this.name = name;
        this.communication = communication;
        this.tiredness = tiredness;
        this.hungriness = hungriness;
    }

    public Occupation getCurrentOccupation() {
        Occupation curPetOccupation;
        if (hungriness > Integer.parseInt(PropertiesAccessPoint.petBehaviorSettings.getProperty(
                "dieingHungrinessThreshold"))) {
            curPetOccupation = Occupation.DIEING;
        } else if (tiredness > Integer.parseInt(PropertiesAccessPoint.petBehaviorSettings.getProperty(
                "sleepingTirednessThreshold"))) {
            curPetOccupation = Occupation.SLEEPING;
        } else if (hungriness > Integer.parseInt(PropertiesAccessPoint.petBehaviorSettings.getProperty(
                "hungrinessThreshold"))) {
            curPetOccupation = Occupation.BEING_HUNGRY;
        } else if (tiredness > Integer.parseInt(PropertiesAccessPoint.petBehaviorSettings.getProperty(
                "tirednessForSleepThreshold"))) {
            curPetOccupation = Occupation.SLEEPING;
        } else if (tiredness > Integer.parseInt(PropertiesAccessPoint.petBehaviorSettings.getProperty(
                "tirednessThreshold"))
                && communication < Integer.parseInt(PropertiesAccessPoint.petBehaviorSettings.getProperty(
                "communicationThreshold"))) {
            curPetOccupation = Occupation.BEING_ANGRY;
        } else if (tiredness > Integer.parseInt(PropertiesAccessPoint.petBehaviorSettings.getProperty(
                "tirednessThreshold"))) {
            curPetOccupation = Occupation.BEING_TIRED;
        } else if (communication < Integer.parseInt(PropertiesAccessPoint.petBehaviorSettings.getProperty(
                "communicationThreshold"))) {
            curPetOccupation = Occupation.BEING_SAD;
        } else {
            curPetOccupation = Occupation.BEING_HAPPY;
        }
        return curPetOccupation;
    }

    public MealType getMealType() {
        return PetSettings.getPetSettingsInstance().getPetSetting(name).getMealType();
    }

    public void tickUpdate() {
        hungriness += Integer.parseInt(PropertiesAccessPoint.petBehaviorSettings.getProperty("tickHungrinessUpdate"));
        tiredness += Integer.parseInt(PropertiesAccessPoint.petBehaviorSettings.getProperty("tickTirednessUpdate"));
        communication += Integer.parseInt(PropertiesAccessPoint.petBehaviorSettings.getProperty("tickCommunicationUpdate"));
    }

    public void hungrinessUpdate() {
        alterHungrinessBy(Integer.parseInt(PropertiesAccessPoint.petBehaviorSettings.getProperty(
                "actionHungrinessUpdate")));
    }

    public void tirednessUpdate() {
        alterTirednessBy(Integer.parseInt(PropertiesAccessPoint.petBehaviorSettings.getProperty(
                "actionTirednessUpdate")));
    }

    public void communicationUpdate() {
        alterCommunicationBy(Integer.parseInt(PropertiesAccessPoint.petBehaviorSettings.getProperty(
                "actionCommunicationUpdate")));
    }

    private int getValueRegardingConstrains(int value) {
        int result = value;
        if (value < 1) {
            result = 1;
        } else if (value > 10) {
            result = 10;
        }
        return result;
    }

    public String getName() {
        return name;
    }

    public int getCommunication() {
        return communication;
    }

    private void alterCommunicationBy(int value) {
        communication = getValueRegardingConstrains(communication + value);
    }

    public int getTiredness() {
        return tiredness;
    }

    private void alterTirednessBy(int value) {
        tiredness = getValueRegardingConstrains(tiredness + value);
    }

    public int getHungriness() {
        return hungriness;
    }

    private void alterHungrinessBy(int value) {
        hungriness = getValueRegardingConstrains(hungriness + value);
    }

    public boolean isAdult() {
        return isAdult;
    }

    public void setAdult(boolean adult) {
        isAdult = adult;
    }

    public boolean isDead() {
        return isDead;
    }

    public void setDead(boolean dead) {
        isDead = dead;
    }
}

