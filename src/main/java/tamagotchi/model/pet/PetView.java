package tamagotchi.model.pet;

import javafx.geometry.Point2D;
import tamagotchi.view.animation.sprite.SpriteAnimation;

public class PetView {
    private SpriteAnimation petAnimation;
    private Point2D petPosition;

    public PetView(SpriteAnimation petAnimation, Point2D petPosition) {
        this.petAnimation = petAnimation;
        this.petPosition = petPosition;
    }

    public SpriteAnimation getPetAnimation() {
        return petAnimation;
    }

    public void setPetAnimation(SpriteAnimation petAnimation) {
        this.petAnimation = petAnimation;
    }

    public Point2D getPetPosition() {
        return petPosition;
    }

    public void setPetPosition(Point2D petPosition) {
        this.petPosition = petPosition;
    }
}
