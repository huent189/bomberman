package bomberman.Entity;

import javafx.scene.image.Image;

public class Wall extends FixedObject {
    private static Image img = new Image(Wall.class.getResource("/sprite/wall.png").toExternalForm());
    public Wall(int xInGrid, int yInGrid) {
        super(xInGrid, yInGrid);
    }

    @Override
    public void update() {
        gc.drawImage(img, x, y, width, heigh);
    }
}
