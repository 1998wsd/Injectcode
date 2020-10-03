package com.wushunda.injectcode.entity;

import java.math.BigInteger;

/**
 * @author shunda wu
 * @date 2020/9/29
 **/
public class TableFile {

    private String tableCatalog;
    /**
     * 表所有者（对于schema的名称）
     */
    private String tableSchema;
    /**
     * 表名
     */
    private String tableName;
    /**
     * 列名
     */
    private String columnName;
    /**
     * 列标识号(第几列)
     */
    private BigInteger ordinalPosition;
    /**
     * 列的默认值
     */
    private String columnDefault;
    /**
     * 列的为空性。如果列允许null，那么该列返回yes。否则，返回no
     */
    private String isNullable;
    /**
     * 系统提供的数据类型
     */
    private String dataType;
    /**
     * 以字符为单位的最大长度，适于二进制数据、字符数据，或者文本和图像数据。否则，返回null。
     */
    private BigInteger characterMaximumLength;
    /**
     * 以字节为单位的最大长度，适于二进制数据、字符数据，或者文本和图像数据。否则，返回nu
     */
    private BigInteger characterOctetLength;
    /**
     * 近似数字数据、精确数字数据、整型数据或货币数据的精度。否则，返回null
     */
    private BigInteger numericPrecision;
    /**
     * 近似数字数据、精确数字数据、整数数据或货币数据的小数位数。否则，返回null
     */
    private BigInteger numericScale;
    /**
     * datetime及sql-92interval数据类型的子类型代码。对于其它数据类型，返回null
     */
    private BigInteger datetimePrecision;
    /**
     * 如果该列是字符数据或text数据类型，那么为字符集返回唯一的名称。否则，返回null
     */
    private String characterSetName;
    /**
     * 如果列是字符数据或text数据类型，那么为排序次序返回唯一的名称。否则，返回null。
     */
    private String collationName;
    /**
     * 类型加长度varchar(255)
     */
    private String columnType;
    /**
     *
     */
    private String columnKey;
    /**
     *
     */
    private String extra;
    /**
     *
     */
    private String privileges;
    /**
     * 注释
     */
    private String columnComment;

    public String getTableCatalog() {
        return tableCatalog;
    }

    public TableFile setTableCatalog(String tableCatalog) {
        this.tableCatalog = tableCatalog;
        return this;
    }

    public String getTableSchema() {
        return tableSchema;
    }

    public TableFile setTableSchema(String tableSchema) {
        this.tableSchema = tableSchema;
        return this;
    }

    public String getTableName() {
        return tableName;
    }

    public TableFile setTableName(String tableName) {
        this.tableName = tableName;
        return this;
    }

    public String getColumnName() {
        return columnName;
    }

    public TableFile setColumnName(String columnName) {
        this.columnName = columnName;
        return this;
    }

    public BigInteger getOrdinalPosition() {
        return ordinalPosition;
    }

    public TableFile setOrdinalPosition(BigInteger ordinalPosition) {
        this.ordinalPosition = ordinalPosition;
        return this;
    }

    public String getColumnDefault() {
        return columnDefault;
    }

    public TableFile setColumnDefault(String columnDefault) {
        this.columnDefault = columnDefault;
        return this;
    }

    public String getIsNullable() {
        return isNullable;
    }

    public TableFile setIsNullable(String isNullable) {
        this.isNullable = isNullable;
        return this;
    }

    public String getDataType() {
        return dataType;
    }

    public TableFile setDataType(String dataType) {
        this.dataType = dataType;
        return this;
    }

    public BigInteger getCharacterMaximumLength() {
        return characterMaximumLength;
    }

    public TableFile setCharacterMaximumLength(BigInteger characterMaximumLength) {
        this.characterMaximumLength = characterMaximumLength;
        return this;
    }

    public BigInteger getCharacterOctetLength() {
        return characterOctetLength;
    }

    public TableFile setCharacterOctetLength(BigInteger characterOctetLength) {
        this.characterOctetLength = characterOctetLength;
        return this;
    }

    public BigInteger getNumericPrecision() {
        return numericPrecision;
    }

    public TableFile setNumericPrecision(BigInteger numericPrecision) {
        this.numericPrecision = numericPrecision;
        return this;
    }

    public BigInteger getNumericScale() {
        return numericScale;
    }

    public TableFile setNumericScale(BigInteger numericScale) {
        this.numericScale = numericScale;
        return this;
    }

    public BigInteger getDatetimePrecision() {
        return datetimePrecision;
    }

    public TableFile setDatetimePrecision(BigInteger datetimePrecision) {
        this.datetimePrecision = datetimePrecision;
        return this;
    }

    public String getCharacterSetName() {
        return characterSetName;
    }

    public TableFile setCharacterSetName(String characterSetName) {
        this.characterSetName = characterSetName;
        return this;
    }

    public String getCollationName() {
        return collationName;
    }

    public TableFile setCollationName(String collationName) {
        this.collationName = collationName;
        return this;
    }

    public String getColumnType() {
        return columnType;
    }

    public TableFile setColumnType(String columnType) {
        this.columnType = columnType;
        return this;
    }

    public String getColumnKey() {
        return columnKey;
    }

    public TableFile setColumnKey(String columnKey) {
        this.columnKey = columnKey;
        return this;
    }

    public String getExtra() {
        return extra;
    }

    public TableFile setExtra(String extra) {
        this.extra = extra;
        return this;
    }

    public String getPrivileges() {
        return privileges;
    }

    public TableFile setPrivileges(String privileges) {
        this.privileges = privileges;
        return this;
    }

    public String getColumnComment() {
        return columnComment;
    }

    public TableFile setColumnComment(String columnComment) {
        this.columnComment = columnComment;
        return this;
    }

    @Override
    public String toString() {
        return "TableFile{" +
                "tableCatalog='" + tableCatalog + '\'' +
                ", tableSchema='" + tableSchema + '\'' +
                ", tableName='" + tableName + '\'' +
                ", columnName='" + columnName + '\'' +
                ", ordinalPosition=" + ordinalPosition +
                ", columnDefault='" + columnDefault + '\'' +
                ", isNullable='" + isNullable + '\'' +
                ", dataType='" + dataType + '\'' +
                ", characterMaximumLength=" + characterMaximumLength +
                ", characterOctetLength=" + characterOctetLength +
                ", numericPrecision=" + numericPrecision +
                ", numericScale=" + numericScale +
                ", datetimePrecision=" + datetimePrecision +
                ", characterSetName='" + characterSetName + '\'' +
                ", collationName='" + collationName + '\'' +
                ", columnType='" + columnType + '\'' +
                ", columnKey='" + columnKey + '\'' +
                ", extra='" + extra + '\'' +
                ", privileges='" + privileges + '\'' +
                ", columnComment='" + columnComment + '\'' +
                '}';
    }
}
