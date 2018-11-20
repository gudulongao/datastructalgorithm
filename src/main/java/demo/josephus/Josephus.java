package demo.josephus;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Josephus环问题
 */
public class Josephus {
    private List<String> list = new LinkedList<String>();

    public Josephus(){}
    public Josephus(String... items){
        if(items == null||items.length == 0){
            return;
        }
        Collections.addAll(list,items);
        System.out.println("build Josephus success !");
    }

    public void kill(int start,int distence){
        if(start <0||start>=list.size()){
            return;
        }
        while (list.size() != 1){
            int index = (start+distence-1)%list.size();
            String item = list.get(index);
            list.remove(index);
            start = index;
            System.out.println("kill: "+item+", list: "+list.toString());
        }
        if(list.size() == 1){
            String last = list.get(0);
            System.out.println("survive: "+last);
        }
    }

    public static void main(String[] args) {
        Josephus demo = new Josephus("A","B","C","D","E","F","G","H");
        demo.kill(0,2);
    }
}
