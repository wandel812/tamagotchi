package tamagotchi.model.meal;

import javafx.geometry.Point2D;
import tamagotchi.view.animation.sprite.SpriteAnimation;

import java.io.Serializable;

public class MealView {
    private SpriteAnimation mealAnimation;
    private Point2D mealPosition;

    public MealView(SpriteAnimation mealAnimation, Point2D mealPosition) {
        this.mealAnimation = mealAnimation;
        this.mealPosition = mealPosition;
        setMealAnimationPosition(mealPosition);
    }

    public SpriteAnimation getMealAnimation() {
        return mealAnimation;
    }

    public void setMealAnimation(SpriteAnimation mealAnimation) {
        this.mealAnimation = mealAnimation;
    }

    public Point2D getMealPosition() {
        return mealPosition;
    }

    public void setMealPosition(Point2D mealPosition) {
        this.mealPosition = mealPosition;
        setMealAnimationPosition(mealPosition);
    }

    private void setMealAnimationPosition(Point2D point) {
        mealAnimation.getTexture().setX(point.getX());
        mealAnimation.getTexture().setY(point.getY());
    }
}
