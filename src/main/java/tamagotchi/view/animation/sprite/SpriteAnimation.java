package tamagotchi.view.animation.sprite;

import javafx.animation.Interpolator;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import tamagotchi.view.animation.TransitionWithTexture;
import tamagotchi.view.animation.sprite.settings.SpriteAnimationSetting;

import java.io.Serializable;

public class SpriteAnimation extends TransitionWithTexture implements Serializable {
    private static final long serialVersionUID = -1657514504284545705L;
    public int frameCnt;
    public int offsetX;
    public int offsetY;
    public int width;
    public int height;
    private double durationInMillis;

    public SpriteAnimation(String imgPath, double durationInMillis, int frameCount, int offsetX, int offsetY,
                           int height, int width) {
        super(new ImageView(imgPath));
        this.frameCnt = frameCount;
        this.offsetX = offsetX;
        this.offsetY = offsetY;
        this.width = width;
        this.height = height;
        this.durationInMillis = durationInMillis;
        setCycleDuration(new Duration(durationInMillis));
        getTexture().setViewport(new Rectangle2D(offsetX, offsetY, width, height));

        setInterpolator(new Interpolator() {
            @Override
            protected double curve(double t) {
                double step = 1.0 / frameCnt;
                double result = (double) frameCnt / 10.0;
                for (int n = 1; n <= frameCnt; n++) {
                    if (t <= n * step) {
                        result = (double) (n - 1) / 10.0;
                        break;
                    }
                }
                return result;
            }
        });
    }

    public SpriteAnimation(String spriteImgPath, SpriteAnimationSetting spriteAnimationSetting) {
        this(
                spriteImgPath,
                spriteAnimationSetting.getDurationInMillis(),
                spriteAnimationSetting.getCount(),
                spriteAnimationSetting.getOffsetX(),
                spriteAnimationSetting.getOffsetY(),
                spriteAnimationSetting.getHeight(),
                spriteAnimationSetting.getWidth()
        );
    }

    public void setOtherAnimation(SpriteAnimationSetting spriteAnimationSetting) {
        frameCnt = spriteAnimationSetting.getCount();
        offsetX = spriteAnimationSetting.getOffsetX();
        offsetY = spriteAnimationSetting.getOffsetY();
        width = spriteAnimationSetting.getWidth();
        height = spriteAnimationSetting.getHeight();
        durationInMillis = spriteAnimationSetting.getDurationInMillis();
        setCycleDuration(new Duration(durationInMillis));
    }

    protected void interpolate(double k) {
        final int index = (int) (k * 10);
        final int x = index * width + offsetX;
        final int y = offsetY;
        getTexture().setViewport(new Rectangle2D(x, y, width, height));
    }

    public double getDurationInMillis() {
        return durationInMillis;
    }

    public void setDurationInMillis(double durationInMillis) {
        this.durationInMillis = durationInMillis;
    }
}