package demo.linklist;

public class OneWayList {
    private OneWayNode head;
    private OneWayNode tail;
    /**
     * 添加数据
     * @param data 数据
     */
    public void add(String data){
        if(data == null|| "".equals(data)){
            return;
        }
        OneWayNode node = new OneWayNode(data);
        add(node);
    }

    /**
     * 添加数据
     * @param node 节点
     */
    public void add(OneWayNode node){
        if(node == null){
            return;
        }
        if (head == null){
            head = node;
            return;
        }
        OneWayNode p = head;
        while (p.getNext() != null){
            p = p.getNext();
        }
        p.setNext(node);
        tail = node;
    }

    /**
     * 删除数据
     * @param data 数据
     * @return 删除结果
     */
    public boolean remove(String data){
        if(data == null||head == null){
            return true;
        }
        if(data.equals(head.getData())){
            System.out.println("delete "+head.toString());
            head = head.getNext();
            return true;
        }
        OneWayNode last = head;
        OneWayNode next = last.getNext();
        while (next != null){
            if (data.equals(next.getData())){
                OneWayNode temp = next.getNext();
                last.setNext(temp);
                System.out.println("delete "+next);
                if (temp == null){
                    tail = last;
                }
                return true;
            }
            last = last.getNext();
            next = next.getNext();
        }
        return false;
    }

    public void insert(int index,String data){
        int size = size();
        if(index<0||index>size){
            return;
        }
        if(index == 0){
            OneWayNode newHead = new OneWayNode(data);
            newHead.setNext(head);
            head = newHead;
            return;
        }
        if(index == size-1){
            OneWayNode newTail = new OneWayNode(data);
            tail.setNext(newTail);
            tail = newTail;
            return;
        }
        OneWayNode last = head;
        OneWayNode next = last.getNext();
        int count = 0;
        while (count<index-1){
            last = last.getNext();
            next = next.getNext();
            count++;
        }
        OneWayNode node = new OneWayNode(data);
        last.setNext(node);
        node.setNext(next);
    }
    /**
     * 计算列表大小
     * @return 大小
     */
    public int size(){
        if(head == null){
            return 0;
        }
        OneWayNode p = head;
        int size = 1;
        while (p.getNext() !=null){
            p = p.getNext();
            size++;
        }
        return size;
    }

    @Override
    public String toString(){
        if(head == null){
            return "empty list";
        }
        OneWayNode p = head;
        StringBuilder buff = new StringBuilder("list [");
        while (p.getNext() !=null){
            buff.append(p.getData()).append(",");
            p = p.getNext();
        }
        buff.append(p.getData()).append("]");
        buff.append("head:").append(head.getData()).append(",tail:").append(tail.getData());
        return buff.toString();
    }
    public static void main(String[] args) {
        OneWayList list = new OneWayList();
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");
        list.add("e");
        list.add("f");
        System.out.println(list.size()+" "+list.toString());
//        list.remove("e");
//        list.remove("f");
//        list.remove("a");
        list.insert(3,"w");
        list.insert(6,"z");
        System.out.println(list.toString());
    }
}
