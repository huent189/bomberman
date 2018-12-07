package bomberman.Entity;


import javafx.scene.image.Image;

public abstract class HideawayObject extends FixedObject{
    protected Image sprite;
    public HideawayObject(int xInGrid, int yInGrid) {
        super(xInGrid, yInGrid);
    }
    public abstract boolean collide(Bomber bomber);//truyền tham số vào đây á uk
    @Override
    public void update() {
        gc.drawImage(sprite, x, y, width, heigh);
    }

}
