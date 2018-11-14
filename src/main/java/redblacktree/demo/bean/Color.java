package redblacktree.demo.bean;

/**
 * 红黑树 颜色定义
 */
public enum Color {
    /**
     * 红色
     */
    RED(1),
    /**
     * 黑色
     */
    BLACK(0);

    private int value;

    Color(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
