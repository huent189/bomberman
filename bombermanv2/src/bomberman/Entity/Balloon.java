package bomberman.Entity;

import bomberman.GameScene;
import javafx.scene.image.Image;

import java.util.Random;

public class Balloon extends Enemy {
    private Random random = new Random();
    public Balloon(int posX, int posY){
        super(posX, posY);
        for (Status d: Status.values()){
            Image[] temp;
            if(d != Status.DEAD){
                temp = new Image[3];
                temp[0] = new Image(getClass().getResource("/sprite/balloom_" + d +"1.png").toExternalForm());
                temp[1] = new Image(getClass().getResource("/sprite/balloom_" + d +"2.png").toExternalForm());
                temp[2] = new Image(getClass().getResource("/sprite/balloom_" + d +"3.png").toExternalForm());
            }
            else {
                temp = new Image[1];
                temp[0] = new Image(getClass().getResource("/sprite/balloom_dead.png").toExternalForm());
            }
            imageLists[d.getVal()] = temp;
        }

    }
    @Override
    public Status generateMove(){
        //TODO: DONE sửa lại hàm (vì random nên nó toàn đứng 1 một chỗ)
        if(isMoving){
            return status;
        }

        int move=random.nextInt(4);
        this.checkCollideWithFixedObject(this.x- GameScene.GAMETILE_SIZE,this.y-GameScene.GAMETILE_SIZE);
        switch (move)
        {
            case 0:
                return Status.GO_LEFT;
            case 1:
                return Status.GO_RIGHT;
            case 2:
                return Status.GO_DOWN;
            case 3:
                return Status.GO_UP;
        }
        return Status.GO_RIGHT;
    }
}
