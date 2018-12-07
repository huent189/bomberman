package bomberman.Entity;

import bomberman.Game;
import bomberman.GameObjectManager;
import bomberman.GameScene;
import bomberman.Sound.SoundPlay;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class Bomber extends MovableObject {
    private static int SPEED = 10;
    private static int maxBomb = 1;
    private int numOfActiveBomb;
    private static int strength = 1;
    private boolean canGoThroughBomb;
    public Bomber(int posX, int posY){
        imageLists = new Image[5][];//số trạng thái và số hình
        x = posX;
        y = posY;
        width = GameScene.GAMETILE_SIZE;
        heigh = GameScene.GAMETILE_SIZE;
        speed = SPEED;
        status = Status.GO_DOWN;
        isMoving = false;
        numOfActiveBomb = 0;
        for (Status d: Status.values()){
            Image[] temp = new Image[3];
            temp[0] = new Image(getClass().getResource("/sprite/player_" + d +".png").toExternalForm());
            temp[1] = new Image(getClass().getResource("/sprite/player_" + d +"_1.png").toExternalForm());
            temp[2] = new Image(getClass().getResource("/sprite/player_" + d +"_2.png").toExternalForm());
            imageLists[d.getVal()] = temp;
        }
    }

    @Override
    public boolean processCollideWithOtherCharacter(MovableObject movableObject) {
        Rectangle2D recThis=new Rectangle(this.x,this.y,this.width,this.heigh);
        Rectangle2D recMovableObject=new Rectangle( movableObject.x,
                movableObject.y,
                movableObject.width,
                movableObject.heigh);
        if(((Rectangle) recThis).intersects(recMovableObject)){
            if(movableObject instanceof Enemy && movableObject.status != Status.DEAD){
                kill();
            }
            return true;
        }
        else return false;
    }

    public void placeBomb(int posXPixel, int posYPixel) {
        if (numOfActiveBomb < maxBomb) {
            new Bomb((posXPixel + this.width / 2) / GameScene.GAMETILE_SIZE, (posYPixel + this.heigh / 2) / GameScene.GAMETILE_SIZE, strength);
            numOfActiveBomb++;
            canGoThroughBomb = true;
        }

    }
    public void handleKeyEvent(KeyEvent event){
        if(event == null){
            return;
        }
        switch (event.getCode()){
            case RIGHT:
                move(Status.GO_RIGHT);
                SoundPlay.playSound(SoundPlay.BOMBER_RUN_SOUND);
                break;
            case LEFT:
                move(Status.GO_LEFT);
                SoundPlay.playSound(SoundPlay.BOMBER_RUN_SOUND);
                break;
            case DOWN:
                move(Status.GO_DOWN);
                SoundPlay.playSound(SoundPlay.BOMBER_RUN_SOUND);
                break;
            case UP:
                move(Status.GO_UP);
                SoundPlay.playSound(SoundPlay.BOMBER_RUN_SOUND);
                break;
            case SPACE:
                placeBomb(x, y);
                SoundPlay.playSound(SoundPlay.BOMBER_RUN_SOUND);
                break;
        }
    }

    @Override
    public void kill() {
        status = Status.DEAD;
        indexOfFrame = 0;
    }
    @Override
    public void update() {
        if(status == Status.DEAD){
            if(indexOfFrame >= 2){
                Game.getInstance().endGame();
            } else {
                indexOfFrame++;
            }
        } else {
            KeyEvent event = Game.getInstance().getEventQueue().poll();
            if(event == null || event.getCode() == KeyCode.SPACE){
                checkCollideWithFixedObject(x, y);
            }
            handleKeyEvent(event);
        }
        gc.drawImage(imageLists[status.getVal()][indexOfFrame % imageLists[status.getVal()].length], x, y, width, heigh);
        if(canGoThroughBomb){
            canGoThroughBomb = false;
            ArrayList<FixedObject> list = manager.getFixedObjectInRect(x, y, width, heigh);
            for (int i = 0; i < list.size(); i++){
                if(list.get(i) instanceof Bomb){
                    canGoThroughBomb = true;
                    break;
                }
            }
        }
}

    @Override
    public boolean checkCollideWithFixedObject(int posX, int posY) {
        GameObjectManager manager = Game.getInstance().getGoManager();
        ArrayList<FixedObject> collideObjs = manager.getFixedObjectInRect(posX, posY, width, heigh);
        for (int i = 0; i < collideObjs.size(); i++){
            if(collideObjs.get(i) instanceof HideawayObject){
                return ((HideawayObject) collideObjs.get(i)).collide(this);
            }
        }
        return super.checkCollideWithFixedObject(posX, posY);
    }
    public int getMaxBomb()
    {
        return this.maxBomb;
    }
    public void setMaxBomb(int maxBomb)
    {
        this.maxBomb=maxBomb;
    }
    public int getStrength()
    {
        return this.strength;
    }
    public void setStrength(int strength)
    {
        this.strength=strength;
    }

    public void decreaseActiveBomb(){
        numOfActiveBomb--;
    }

    public boolean checkGoThrough(){
        return canGoThroughBomb;
    }
}
