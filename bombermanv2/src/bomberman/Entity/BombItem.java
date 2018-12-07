package bomberman.Entity;

import javafx.scene.image.Image;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class BombItem extends HideawayObject {

    public BombItem(int xInGrid, int yInGrid) {
        super(xInGrid, yInGrid);
        sprite = new Image(SpeedItem.class.getResource("/sprite/powerup_bombs.png").toExternalForm());
    }

    @Override
    public boolean collide(Bomber bomber) {
        bomber.setMaxBomb(bomber.getMaxBomb() + 1);
        remove();
        return true;
    }
}
