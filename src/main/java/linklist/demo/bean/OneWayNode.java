package linklist.demo.bean;

public class OneWayNode {
    private String data;
    private OneWayNode next;

    public OneWayNode(String data, OneWayNode next) {
        this.data = data;
        this.next = next;
    }

    public OneWayNode(String data) {
        this.data = data;
    }

    public OneWayNode(){}

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public OneWayNode getNext() {
        return next;
    }

    public void setNext(OneWayNode next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return "OneWayNode{" +
                "data='" + data + '\'' +
                '}';
    }
}
