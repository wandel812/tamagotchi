package tamagotchi.view.animation.motion.settings;

public class MotionAnimationSetting {
    private String motionName;
    private double durationInMillis;

    public MotionAnimationSetting(String motionName, double durationInMillis) {
        this.motionName = motionName;
        this.durationInMillis = durationInMillis;
    }

    public String getMotionName() {
        return motionName;
    }

    public void setMotionName(String motionName) {
        this.motionName = motionName;
    }

    public double getDurationInMillis() {
        return durationInMillis;
    }

    public void setDurationInMillis(double durationInMillis) {
        this.durationInMillis = durationInMillis;
    }
}
