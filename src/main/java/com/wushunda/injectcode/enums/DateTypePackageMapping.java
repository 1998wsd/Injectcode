package com.wushunda.injectcode.enums;

/**
 * @author shunda wu
 * @date 2020/9/30
 * @description mysql类型名称对应Java需要引入的包名
 **/
public enum DateTypePackageMapping {

    /**
     *
     */
    BIGINT("BIGINT", "java.math.BigInteger"),
    DATE("DATE", "java.util.Date"),
    DATETIME("DATETIME", "java.sql.Timestamp"),
    TIME("TIME", "java.sql.Time"),
    TIMESTAMP("TIMESTAMP", "java.sql.Timestamp"),
    YEAR("YEAR", "java.sql.Date"),
    SMALLINT("SMALLINT", "java.math.BigDecimal"),
    DECIMAL("DECIMAL", "java.math.BigDecimal");

    private final String key;
    private final String value;

    DateTypePackageMapping(String key, String value) {
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
        for (DateTypePackageMapping ele : values()) {
            if (ele.getKey().equalsIgnoreCase(key)) {
                return ele.getValue();
            }
        }
        return null;
    }

}
