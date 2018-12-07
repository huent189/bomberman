package bomberman;


import bomberman.Entity.FixedObject;

import java.util.LinkedList;

public class ObjectStack {
    private LinkedList<FixedObject> list = new LinkedList<>();

    public FixedObject getLast(){
        return list.peekFirst();
    }

    public void add(FixedObject obj){
        list.push(obj);
    }

    public void removeLast(){
        list.pop();
    }

    public void remove(FixedObject obj){
        list.remove(obj);
    }
}
