package bomberman.Entity;

import javafx.scene.image.Image;

public class FlameItem extends HideawayObject {

    public FlameItem(int xInGrid, int yInGrid) {
        super(xInGrid, yInGrid);
        sprite = new Image(FlameItem.class.getResource("/sprite/powerup_flames.png").toExternalForm());
    }

    @Override
    public boolean collide(Bomber bomber) {
        bomber.setStrength(bomber.getStrength() + 1);
        remove();
        return true;
    }

}
