package tamagotchi.view.animation;

import javafx.animation.Transition;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;


public abstract class TransitionWithTexture extends Transition {
    private ImageView texture;

    public TransitionWithTexture(ImageView texture) {
        super();
        this.texture = texture;
        getTexture().setViewport(new Rectangle2D(0, 0, 0, 0));
    }

    public ImageView getTexture() {
        return texture;
    }

    public void setTexture(ImageView texture) {
        this.texture = texture;
    }
}
