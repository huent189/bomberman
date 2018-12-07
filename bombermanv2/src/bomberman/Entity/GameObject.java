package bomberman.Entity;

import bomberman.Game;
import javafx.scene.canvas.GraphicsContext;
public abstract class GameObject {
    protected int x;
    protected int y;
    protected int width;
    protected int heigh;
    protected GraphicsContext gc = Game.getInstance().getGameScene().getGraphicsContext2D();
    public abstract void update();
}
