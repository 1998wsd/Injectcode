package com.wushunda.injectcode.enums;

/**
 * @author shunda wu
 * @date 2020/9/30
 * @description mysql类型名称对应Java类型名称
 **/
public enum DateTypeMapping {

    /**
     *
     */
    VARCHAR("VARCHAR", "String"),
    CHAR("CHAR", "String"),
    BLOB("BLOB", "Byte"),
    TEXT("TEXT", "String"),
    INTEGER("INTEGER", "Long"),
    TINYINT("TINYINT", "Integer"),
    SMALLINT("SMALLINT", "Integer"),
    MEDIUMINT("MEDIUMINT", "Integer"),
    BIT("BIT", "Boolean"),
    BIGINT("BIGINT", "BigInteger"),
    FLOAT("FLOAT", "Float"),
    DOUBLE("DOUBLE", "Double"),
    DECIMAL("DECIMAL", "BigDecimal"),
    BOOLEAN("BOOLEAN", "Integer"),
    ID("ID", "Long"),
    DATE("Date", "Date"),
    TIME("TIME", "TIME"),
    DATETIME("DATETIME", "Timestamp"),
    TIMESTAMP("TIMESTAMP", "Timestamp"),
    YEAR("BOOLEAN", "Date");

    private final String key;
    private final String value;

    DateTypeMapping(String key, String value) {
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
        for (DateTypeMapping ele : values()) {
            if (ele.getKey().equalsIgnoreCase(key)) {
                return ele.getValue();
            }
        }
        return key;
    }

}
