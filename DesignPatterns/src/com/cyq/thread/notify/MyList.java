package src.com.cyq.thread.notify;

import java.util.ArrayList;
import java.util.List;

public class MyList {
    volatile private List<String> list = new ArrayList();

    public void add() {
        list.add("any string");
    }

    public int size() {
        return list.size();
    }

    public void clear(){
        list.clear();
    }
}
