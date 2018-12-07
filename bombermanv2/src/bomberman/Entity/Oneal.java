package bomberman.Entity;

import bomberman.Game;
import bomberman.GameScene;
import javafx.scene.image.Image;

import java.util.Random;

public class Oneal extends Enemy {
    public Oneal(int posX, int posY){
        super(posX, posY);
        this.speed=5;
        for (Status d: Status.values()){
            Image[] temp;
            if(d != Status.DEAD){
                temp = new Image[3];
                temp[0] = new Image(getClass().getResource("/sprite/oneal_" + d +"1.png").toExternalForm());
                temp[1] = new Image(getClass().getResource("/sprite/oneal_" + d +"2.png").toExternalForm());
                temp[2] = new Image(getClass().getResource("/sprite/oneal_" + d +"3.png").toExternalForm());
            }
            else {
                temp = new Image[1];
                temp[0] = new Image(getClass().getResource("/sprite/oneal_dead.png").toExternalForm());
            }
            imageLists[d.getVal()] = temp;
        }
    }
    @Override
    public Status generateMove() {
        //TODO: viết hàm sinh bước tiếp theo cho quá
//        if(isMoving)
//        {
//            this.status=status;
//        }
//        else
//        {
//            if((this.x<Game.getInstance().getGoManager().getBomber().x&&canMove(this.x+5,this.y))
//                        this.status=Status.GO_RIGHT;
//            else if((this.y>Game.getInstance().getGoManager().getBomber().x&&canMove(this.x,this.y+5))||
//                    (this.y<Game.getInstance().getGoManager().getBomber().x&&canMove(this.x,this.y+5)))
//                this.status= Status.GO_DOWN;
//            else if((this.x>Game.getInstance().getGoManager().getBomber().x&&canMove(this.x-5,this.y))||
//                    (this.x<Game.getInstance().getGoManager().getBomber().x&&canMove(this.x-5,this.y)))
//                this.status= Status.GO_LEFT;
//            else if((this.y<Game.getInstance().getGoManager().getBomber().x&&canMove(this.x,this.y-5))||
//                    (this.y>Game.getInstance().getGoManager().getBomber().x&&canMove(this.x,this.y-5)))
//                this.status= Status.GO_UP;
//        }
//
//        return  this.status;
//        if(isMoving)
//        {
//            this.status=status;
//        }
////        if(!isMoving)
//        else
//        {
//            Random random=new Random();
//            int randomMove= random.nextInt(2);
//            if(randomMove==0)
//            {
//                status=goRow();
//                if(status==Status.GO_DOWN&&canMove(this.x,this.y+1)||this.canMove(this.x,this.y-1))
//                    status=goColum();
//            }
//            else
//            {
//                status=goColum();
//                if(status==Status.GO_RIGHT&&(canMove(this.x+1,this.y)||canMove(this.x-1,this.y)))
//                {
//                    status=goRow();
//                }
//            }
//        }
////        else this.status=status;
//        return status;
        Random random=new Random();
        int move;
        if(Game.getInstance().getGoManager().getBomber().isMoving==false)
        {
            if(this.isMoving){
                this.status=status;
            }
            else
            {
                move=random.nextInt(4);
                switch (move)
                {
                    case 0:
                        if (canMove(this.x-5,this.y))
                        {
                            this.status=Status.GO_LEFT;
                        }
                        else
                        {
                            this.status=randomColum();
                        }
                        break;
                    case 1:
                        if (canMove(this.x+5,this.y))
                        {
                            this.status=Status.GO_RIGHT;
                        }
                        else
                        {
                            this.status=randomColum();
                        }
                        break;
                    case 2:
                        if (canMove(this.x,this.y+5))
                        {
                            this.status=Status.GO_DOWN;
                        }
                        else
                        {
                            this.status=randomRow();
                        }
                        break;
                    case 3:
                        if (canMove(this.x,this.y-5))
                        {
                            this.status=Status.GO_UP;
                        }
                        else
                        {
                            this.status=randomRow();
                        }
                        break;
                }
            }

        }
        else
        {
            if(Game.getInstance().getGoManager().getBomber().isMoving)
            {
                if(this.isMoving)
                {
                    if(Game.getInstance().getGoManager().getBomber().status==Status.GO_DOWN)
                    {
                        if(this.canMove(this.x,this.y-5))
                        {
                            this.status=Status.GO_UP;
                        }
                        else if (this.canMove(Status.GO_DOWN)) this.status=Status.GO_DOWN;
                        else this.status=randomRow();
                    }
                    else if(Game.getInstance().getGoManager().getBomber().status==Status.GO_UP)
                    {
                        if(this.canMove(this.x,this.y+5))
                        {
                            this.status=Status.GO_DOWN;
                        }
                        else if (this.canMove(Status.GO_UP)) this.status=Status.GO_UP;
                        else this.status=randomRow();
                    }
                    else if(Game.getInstance().getGoManager().getBomber().status==Status.GO_RIGHT)
                    {
                        if(this.canMove(this.x-5,this.y))
                        {
                            this.status=Status.GO_LEFT;
                        }
                        else if (this.canMove(Status.GO_RIGHT)) this.status=Status.GO_RIGHT;
                        else this.status=randomColum();
                    }
                    else if(Game.getInstance().getGoManager().getBomber().status==Status.GO_LEFT)
                    {
                        if (this.canMove(this.x+5,this.y))
                        {
                            this.status = Status.GO_RIGHT;
                        }
                        else if (this.canMove(Status.GO_LEFT)) this.status=Status.GO_LEFT;
                        else this.status=randomColum();

                    }
                }
                else
                {
                    move=random.nextInt(4);
                    switch (move) {
                        case 0:
                            if (canMove(this.x-5,this.y))
                            {
                              this.status=Status.GO_LEFT;
                            }
                            else
                            {
                                this.status=Status.GO_RIGHT;
                            }
                            break;
                        case 1:
                            if (canMove(this.x+5,this.y))
                            {
                                this.status=Status.GO_RIGHT;
                            }
                            else
                            {
                                this.status=Status.GO_LEFT;
                            }
                            break;
                        case 2:
                            if (canMove(this.x,this.y+5))
                            {
                                this.status=Status.GO_DOWN;
                            }
                            else
                            {
                                this.status=Status.GO_UP;
                            }
                            break;
                        case 3:
                            if (canMove(this.x,this.y-5))
                            {
                                this.status=Status.GO_UP;
                            }
                            else
                            {
                                this.status=Status.GO_DOWN;
                            }
                            break;
                    }
                }
            }

        }

        return this.status;

    }

    public Status randomColum()
    {
        if (this.canMove(Status.GO_UP)) return Status.GO_UP;
        else if(this.canMove(Status.GO_DOWN)) return Status.GO_DOWN;
        return Status.GO_LEFT;
    }
    public Status randomRow()
    {
        if (this.canMove(Status.GO_RIGHT)) return Status.GO_RIGHT;
        else if(this.canMove(Status.GO_LEFT)) return Status.GO_LEFT;
        return Status.GO_UP;
    }

    private boolean canMove(Status status)
    {
        if(status==Status.GO_UP&&this.canMove(this.x,this.y-5)) return true;
        else if(status==Status.GO_DOWN&&this.canMove(this.x,this.y+5)) return true;
        else if(status==Status.GO_RIGHT&&this.canMove(this.x+5,this.y)) return true;
        else if(status==Status.GO_LEFT&&this.canMove(this.x-5,this.y)) return true;
        return false;
    }
}
