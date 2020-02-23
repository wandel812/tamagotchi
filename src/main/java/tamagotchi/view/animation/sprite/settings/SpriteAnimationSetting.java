package tamagotchi.view.animation.sprite.settings;

import java.util.Objects;

public class SpriteAnimationSetting {
    private String animationName;
    private int count;
    private int offsetX;
    private int offsetY;
    private int width;
    private int height;
    private double durationInMillis;

    public SpriteAnimationSetting(String animationName, int count, int offsetX, int offsetY, int width, int height,
                                  double durationInMillis) {
        this.animationName = animationName;
        this.count = count;
        this.offsetX = offsetX;
        this.offsetY = offsetY;
        this.width = width;
        this.height = height;
        this.durationInMillis = durationInMillis;
    }

    public String getAnimationName() {
        return animationName;
    }

    public void setAnimationName(String animationName) {
        this.animationName = animationName;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getOffsetX() {
        return offsetX;
    }

    public void setOffsetX(int offsetX) {
        this.offsetX = offsetX;
    }

    public int getOffsetY() {
        return offsetY;
    }

    public void setOffsetY(int offsetY) {
        this.offsetY = offsetY;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public double getDurationInMillis() {
        return durationInMillis;
    }

    public void setDurationInMillis(double durationInMillis) {
        this.durationInMillis = durationInMillis;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SpriteAnimationSetting that = (SpriteAnimationSetting) o;
        return count == that.count &&
                offsetX == that.offsetX &&
                offsetY == that.offsetY &&
                width == that.width &&
                height == that.height &&
                Double.compare(that.durationInMillis, durationInMillis) == 0 &&
                animationName.equals(that.animationName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(animationName, count, offsetX, offsetY, width, height, durationInMillis);
    }
}
