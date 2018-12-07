package bomberman.Entity;

public enum Status {
    GO_LEFT(0), GO_RIGHT(1), GO_DOWN(2), GO_UP(3), DEAD(4);
    private int val;

    Status(int value){
        val = value;
    }

    public int getVal() {
        return val;
    }

    @Override
    public String toString() {
        switch (val){
            case 0:
                return "left";
            case 1:
                return "right";
            case 2:
                return "down";
            case 3:
                return "up";
            default:
                return "dead";
        }
    }
}
