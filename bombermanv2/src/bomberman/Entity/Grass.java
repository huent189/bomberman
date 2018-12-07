package bomberman.Entity;

import javafx.scene.image.Image;

public class Grass extends FixedObject {
    private static Image img = new Image(Wall.class.getResource("/sprite/grass.png").toExternalForm());
    public Grass(int xInGrid, int yInGrid) {
        super(xInGrid, yInGrid);
    }

    @Override
    public void update() {
        gc.drawImage(img, x, y, width, heigh);
    }

}
