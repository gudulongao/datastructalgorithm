package demo.tree.redblacktree;

/**
 * 颜色定义
 */
public enum ColourType {
    /**
     * 红色
     */
    RED("red", 0),
    /**
     * 黑色
     */
    BLACK("black", 1);
    private String strValue;
    private int intValue;

    ColourType(String strValue, int intValue) {
        this.strValue = strValue;
        this.intValue = intValue;
    }

    public String getStrValue() {
        return strValue;
    }

    public int getIntValue() {
        return intValue;
    }
}
