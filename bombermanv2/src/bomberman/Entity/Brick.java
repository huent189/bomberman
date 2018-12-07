package bomberman.Entity;

import javafx.scene.image.Image;

public class Brick extends FixedObject{
    private static Image img = new Image(Wall.class.getResource("/sprite/brick.png").toExternalForm());
    public Brick(int xInGrid, int yInGrid) {
        super(xInGrid, yInGrid);
    }

    @Override
    public void update() {
        //TODO: DONE -h
        //DONE
        gc.drawImage(img, x, y, width, heigh);
    }

}
