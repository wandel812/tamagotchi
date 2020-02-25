package tamagotchi.model.pet;

import tamagotchi.data.PropertiesAccessPoint;
import tamagotchi.model.meal.MealType;
import tamagotchi.model.pet.settings.PetSettings;

import java.io.Serializable;

public class Pet implements Serializable {
    private static final long serialVersionUID = 2216574448288194471L;
    private String name;
    private int communication;
    private int tiredness;
    private int hungriness;
    private boolean isAdult;
    private boolean isDead;
    private boolean isSleeping;

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
            isSleeping = false;
            isDead = true;
            curPetOccupation = Occupation.DIEING;
        } else if (hungriness > Integer.parseInt(PropertiesAccessPoint.petBehaviorSettings.getProperty(
                "hungrinessThreshold"))) {
            isSleeping = false;
            curPetOccupation = Occupation.BEING_HUNGRY;
        } else if (tiredness > Integer.parseInt(PropertiesAccessPoint.petBehaviorSettings.getProperty(
                "sleepingTirednessThreshold"))) {
            isSleeping = true;
            curPetOccupation = Occupation.SLEEPING;
        } else {
            if (isSleeping && tiredness > Integer.parseInt(PropertiesAccessPoint.petBehaviorSettings.getProperty(
                    "wakingUpThreshold"))) {
                curPetOccupation = Occupation.SLEEPING;
                isSleeping = true;
            } else {
                if (tiredness > Integer.parseInt(PropertiesAccessPoint.petBehaviorSettings.getProperty(
                        "tirednessThreshold"))
                        && communication < Integer.parseInt(PropertiesAccessPoint.petBehaviorSettings.getProperty(
                        "communicationThreshold"))) {
                    curPetOccupation = Occupation.BEING_ANGRY;
                } else {
                    isSleeping = false;
                    if (tiredness > Integer.parseInt(PropertiesAccessPoint.petBehaviorSettings.getProperty(
                            "tirednessThreshold"))) {
                        curPetOccupation = Occupation.BEING_TIRED;
                    } else if (communication < Integer.parseInt(PropertiesAccessPoint.petBehaviorSettings.getProperty(
                            "communicationThreshold"))) {
                        curPetOccupation = Occupation.BEING_SAD;
                    } else {
                        curPetOccupation = Occupation.BEING_HAPPY;
                    }
                }
            }
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
        alterTirednessBy(
                Integer.parseInt(PropertiesAccessPoint.petBehaviorSettings.getProperty("actionTirednessUpdate"))
                        - Integer.parseInt(PropertiesAccessPoint.petBehaviorSettings.getProperty("tickTirednessUpdate"))
        );
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

    public void setName(String name) {
        this.name = name;
    }

    public void setCommunication(int communication) {
        this.communication = communication;
    }

    public void setTiredness(int tiredness) {
        this.tiredness = tiredness;
    }

    public void setHungriness(int hungriness) {
        this.hungriness = hungriness;
    }

    public boolean isSleeping() {
        return isSleeping;
    }

    public void setSleeping(boolean sleeping) {
        isSleeping = sleeping;
    }
}

