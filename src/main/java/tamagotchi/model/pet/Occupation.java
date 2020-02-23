package tamagotchi.model.pet;

public enum Occupation {
    SLEEPING("sleep", "Zzz..."),
    DIEING("beStill", "X_X X_X X_X"),
    BEING_TIRED("beTired", "*yawn* *yawn* *yawn*"),
    BEING_HUNGRY("beHungry", "Wanna eat!"),
    BEING_ANGRY("beAngry", ">_< >_< >_<"),
    BEING_SAD("beSad", "=( =( =("),
    BEING_HAPPY("beHappy", "I'ma okay ^___^ ");

    private String animationName;
    private String statusMessage;

    private Occupation(String animationName, String statusMessage) {
        this.animationName = animationName;
        this.statusMessage = statusMessage;
    }

    public String getAnimationName() {
        return animationName;
    }

    public String getStatusMessage() {
        return statusMessage;
    }
}
