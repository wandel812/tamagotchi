package tamagotchi.view;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class ProgressBarProperties {
    private static DoubleProperty hungriness;
    private static DoubleProperty tiredness;
    private static DoubleProperty communication;
    private static final double DIVIDER = 10.0;

    static {
        hungriness = new SimpleDoubleProperty(0);
        tiredness = new SimpleDoubleProperty(0);
        communication = new SimpleDoubleProperty(0);
    }

    public static double getHungriness() {
        return hungriness.get();
    }

    public static DoubleProperty hungrinessProperty() {
        return hungriness;
    }

    public static void setHungriness(int hungriness) {
        ProgressBarProperties.hungriness.set(hungriness / DIVIDER);
    }

    public static double getTiredness() {
        return tiredness.get();
    }

    public static DoubleProperty tirednessProperty() {
        return tiredness;
    }

    public static void setTiredness(int tiredness) {
        ProgressBarProperties.tiredness.set(tiredness / DIVIDER);
    }

    public static double getCommunication() {
        return communication.get();
    }

    public static DoubleProperty communicationProperty() {
        return communication;
    }

    public static void setCommunication(int communication) {
        ProgressBarProperties.communication.set(communication / DIVIDER);
    }
}
