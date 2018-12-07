package bomberman.Entity;

import bomberman.Game;
import bomberman.GameObjectManager;
import javafx.scene.image.Image;

public class Flame extends FixedObject{
    public enum FlameStatus{
        horizontal, horizontal_left_last, horizontal_right_last, vertical, vertical_down_last,
        vertical_top_last
    }
    private int indexOfImage = 0;
    private long startTime;
    private Image[] imageList;
    public Flame(int xInGrid, int yInGrid, FlameStatus status) {
        super(xInGrid, yInGrid);
        imageList = new Image[3];
        startTime = System.currentTimeMillis();
        for (int i = 0; i < 3; i++){
            imageList[i] = new Image(getClass().getResource("/sprite/explosion_" + status + i + ".png").toExternalForm());
        }
        GameObjectManager manager = Game.getInstance().getGoManager();
        if(!(manager.getFixedObjectAt(xInGrid, yInGrid) instanceof Grass)){
            manager.removeAt(xInGrid, yInGrid);
        }
        manager.addObject(this);
    }

    @Override
    public void update() {
        /*if(System.currentTimeMillis() - startTime > 150){
            startTime = System.currentTimeMillis();

        }*/
        if(indexOfImage >= imageList.length){
            remove();
        } else {
            gc.drawImage(imageList[indexOfImage], x, y, width, heigh);
            indexOfImage++;
        }
    }
}
