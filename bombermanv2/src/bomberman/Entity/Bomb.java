package bomberman.Entity;


import bomberman.Game;
import bomberman.GameObjectManager;
import bomberman.Sound.SoundPlay;
import javafx.scene.image.Image;

public class Bomb extends FixedObject{
    private static final int TIME_ALIVE = 2000;
    private Image[][] sprites;
    private boolean isExploded = false;
    private int indexOfSprite = 0;
    private long timeToExplode;
    private int strength;

    public Bomb(int xInGrid, int yInGrid, int strength) {
        super(xInGrid, yInGrid);
        timeToExplode = System.currentTimeMillis() + TIME_ALIVE;
        this.strength = strength;
        sprites = new Image[2][3];
        for (int i = 0; i < 3; i++){
            sprites[0][i] = new Image(Bomb.class.getResource("/sprite/bomb_" + i +".png").toExternalForm());
            sprites[1][i] = new Image(Bomb.class.getResource("/sprite/bomb_exploded" + i +".png").toExternalForm());
        }
        Game.getInstance().getGoManager().addObject(this);
    }

    @Override
    public void update() {
        indexOfSprite++;
        if(!isExploded && timeToExplode < System.currentTimeMillis()){
            isExploded = true;
            indexOfSprite = 0;
            explode();
            Game.getInstance().getGoManager().getBomber().decreaseActiveBomb();
        }
        gc.drawImage(sprites[isExploded ? 1: 0][indexOfSprite % 3], x, y, width, heigh);
        if(isExploded)
        {
            SoundPlay.playSound(SoundPlay.BOMB_FIRE);
        }
        if(isExploded && indexOfSprite >= 2){

            Game.getInstance().getGoManager().removeObject(this);
        }
    }

    public void explode(){
        //create flame
        GameObjectManager manager = Game.getInstance().getGoManager();
        for (int i = xInGrid + 1; i <= xInGrid + strength; i++){
            if(manager.getFixedObjectAt(i, yInGrid) == null || manager.getFixedObjectAt(i, yInGrid) instanceof Wall ){
                i--;
                if(i != xInGrid && manager.getFixedObjectAt(i, yInGrid) != null){
                    new Flame(i , yInGrid, Flame.FlameStatus.horizontal_right_last);
                }
                break;
            }
            if(i == xInGrid + strength){
                new Flame(i, yInGrid, Flame.FlameStatus.horizontal_right_last);
            } else
            new Flame(i, yInGrid, Flame.FlameStatus.horizontal);
        }

        for (int i = xInGrid - 1; i >= xInGrid - strength; i--){
            if(manager.getFixedObjectAt(i, yInGrid) == null || manager.getFixedObjectAt(i, yInGrid) instanceof Wall ){
                i++;
                if(i != xInGrid && manager.getFixedObjectAt(i, yInGrid) != null){
                    new Flame(i, yInGrid, Flame.FlameStatus.horizontal_left_last);
                }
                break;
            }
            if(i == xInGrid - strength){
                new Flame(i, yInGrid, Flame.FlameStatus.horizontal_right_last);
            } else
            new Flame(i, yInGrid, Flame.FlameStatus.horizontal);
        }

        for (int i = yInGrid + 1; i <= yInGrid + strength; i++){
            if(manager.getFixedObjectAt(xInGrid, i) == null || manager.getFixedObjectAt(xInGrid, i) instanceof Wall ){
                i--;
                if(i != yInGrid && manager.getFixedObjectAt(xInGrid, i ) != null){
                    new Flame(xInGrid, i, Flame.FlameStatus.vertical_down_last);
                }
                break;
            }
            if(i == yInGrid + strength){
                new Flame(xInGrid, i, Flame.FlameStatus.vertical_down_last);
            } else
            new Flame(xInGrid, i, Flame.FlameStatus.vertical);
        }

        for (int i = yInGrid - 1; i >= yInGrid - strength; i--){
            if(manager.getFixedObjectAt(xInGrid, i) == null || manager.getFixedObjectAt(xInGrid, i) instanceof Wall ){
                i++;
                if(i != yInGrid && manager.getFixedObjectAt(xInGrid, i) != null ){
                    new Flame(xInGrid, i, Flame.FlameStatus.vertical_top_last);
                }
                break;
            }
            if(i == yInGrid - strength){
                new Flame(xInGrid, i, Flame.FlameStatus.vertical_top_last);
            } else
            new Flame(xInGrid, i, Flame.FlameStatus.vertical);
        }
    }

    public boolean isExploded(){
        return isExploded;
    }
}
