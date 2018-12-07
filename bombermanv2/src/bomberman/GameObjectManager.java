package bomberman;

import bomberman.Entity.*;

import java.util.ArrayList;

public class GameObjectManager {

    private int numOfEnemy;
    private ArrayList<MovableObject> characters = new ArrayList<MovableObject>();
    private ObjectStack[][] fixedObjectList;
    private Bomber bomber;
    private int width;
    private int height;
    public GameObjectManager(int width, int height){
        this.width = width;
        this.height = height;
        fixedObjectList = new ObjectStack[height][width];
    }

    public ArrayList<MovableObject> getCharacters() {
        return characters;
    }

    public ObjectStack[][] getFixedObjectList() {
        return fixedObjectList;
    }

    public void addObject(GameObject obj){
        if(obj instanceof MovableObject){
            characters.add((MovableObject) obj);
            if(obj instanceof Bomber){
                bomber = (Bomber) obj;
            } else if (obj instanceof Enemy){
                numOfEnemy++;
            }
        }
        else {
            FixedObject fixedRef = (FixedObject) obj;
            int x = fixedRef.getxInGrid();
            int y = fixedRef.getyInGrid();
            if(fixedObjectList[y][x] == null){
                fixedObjectList[y][x] = new ObjectStack();
            }
            fixedObjectList[y][x].add(fixedRef);
        }
    }
    public void removeAt(int xInGrid, int yInGrid){
        fixedObjectList[yInGrid][xInGrid].removeLast();
    }
    public ArrayList<FixedObject> getFixedObjectInRect(int x, int y, int width, int heigh){
        int xInGridLeft, xInGridRight, yInGridUp, yInGridDown;
        xInGridLeft = (x + 2) / GameScene.GAMETILE_SIZE;
        xInGridRight = (x + width - 3) / GameScene.GAMETILE_SIZE;
        yInGridUp = (y + 2) / GameScene.GAMETILE_SIZE;
        yInGridDown = (y + heigh - 3) / GameScene.GAMETILE_SIZE;
        ArrayList<FixedObject> result = new ArrayList<>();
        result.add(fixedObjectList[yInGridUp][xInGridLeft].getLast());
        result.add(fixedObjectList[yInGridDown][xInGridLeft].getLast());
        result.add(fixedObjectList[yInGridUp][xInGridRight].getLast());
        result.add(fixedObjectList[yInGridDown][xInGridRight].getLast());
        return result;
    }
    public FixedObject getFixedObjectAt(int xInGrid, int yInGrid){
        if(xInGrid < 0 || xInGrid > width || yInGrid < 0 || yInGrid > height){
            return null;
        }
        return (fixedObjectList[yInGrid][xInGrid].getLast());
    }
    public void removeObject(GameObject obj){
        if(obj instanceof FixedObject){
            FixedObject fixedRef = (FixedObject) obj;
            int x = fixedRef.getxInGrid();
            int y = fixedRef.getyInGrid();
            fixedObjectList[y][x].remove(fixedRef);
        }
        if (obj instanceof MovableObject){
            characters.remove(obj);
        }
    }

    public Bomber getBomber() {
        return bomber;
    }
    public int getNumOfEnemy()
    {
        return numOfEnemy;
    }
    public void addEnemy(int add)
    {
        numOfEnemy += add;
    }

    public void reset(){
        fixedObjectList = new ObjectStack[height][width];
        characters = new ArrayList<>();
        numOfEnemy = 0;
    }
}
