package bomberman.Entity;

import javafx.scene.image.Image;

public class SpeedItem extends HideawayObject {

    public SpeedItem(int xInGrid, int yInGrid) {
        super(xInGrid, yInGrid);
        sprite = new Image(SpeedItem.class.getResource("/sprite/powerup_speed.png").toExternalForm());
    }

    @Override
    public boolean collide(Bomber bomber) {
        bomber.speed*=2;
        remove();
        return true;
    }
}
