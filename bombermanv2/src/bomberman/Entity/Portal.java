package bomberman.Entity;

import bomberman.Game;
import javafx.scene.image.Image;

public class Portal extends HideawayObject {

    public Portal(int xInGrid, int yInGrid) {
        super(xInGrid, yInGrid);
        sprite = new Image(Wall.class.getResource("/sprite/portal.png").toExternalForm());
    }

    @Override
    public boolean collide(Bomber bomber) {
        //TODO: xử lý va chạm với bomber
        if(Game.getInstance().getGoManager().getNumOfEnemy()<=0)
        {
            Game.getInstance().loadNextLevel();
            return true;
        }
        return false;
    }
}
