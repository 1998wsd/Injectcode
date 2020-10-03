package com.wushunda.injectcode.enums;

/**
 * @author wsd
 * PRI主键约束　　UNI唯一约束　　MUL可以重复
 */
public enum ColumnKey {
    /**
     *
     */
    PRI("PRI", "主键约束"),
    UNI("UNI", "唯一约束"),
    MUL("MUL", "可以重复");

    private final String key;
    private final String value;

    ColumnKey(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }


    public String getValue() {
        return value;
    }

    public static String getValue(String key) {
        for (ColumnKey ele : values()) {
            if (ele.getKey().equalsIgnoreCase(key)) {
                return ele.getValue();
            }
        }
        return null;
    }

}
