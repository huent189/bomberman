package bomberman.Entity;
import bomberman.Game;
import bomberman.GameScene;

public abstract class FixedObject extends GameObject {
    protected int xInGrid;
    protected int yInGrid;
    public FixedObject(int xInGrid, int yInGrid){
        this.xInGrid = xInGrid;
        this.yInGrid = yInGrid;
        x = xInGrid * GameScene.GAMETILE_SIZE;
        y = yInGrid * GameScene.GAMETILE_SIZE;
        width = GameScene.GAMETILE_SIZE;
        heigh = GameScene.GAMETILE_SIZE;
    }

    public int getxInGrid() {
        return xInGrid;
    }

    public int getyInGrid() {
        return yInGrid;
    }

    public void remove(){
        Game.getInstance().getGoManager().removeObject(this);
    }
}
