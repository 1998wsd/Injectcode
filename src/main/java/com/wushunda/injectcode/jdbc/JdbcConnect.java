package com.wushunda.injectcode.jdbc;


import com.wushunda.injectcode.entity.TableFile;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wsd
 */
public class JdbcConnect {

    public String url;
    public String user;
    public String pwd;
    private String driver;

    public JdbcConnect(String url, String user, String pwd, String driver) {
        this.url = url;
        this.user = user;
        this.pwd = pwd;
        this.driver = driver;
    }

    public Connection getConnect() throws ClassNotFoundException, SQLException {
        Class.forName(driver);
        //建立数据库对象
        return DriverManager.getConnection(url, user, pwd);
    }

    public List<String> getTableNames() throws SQLException, ClassNotFoundException {
        List<String> tableNames = new ArrayList<>();
        Connection connection = getConnect();
        //建立操作对象
        Statement stmt = getConnect().createStatement();
        //结果集
        ResultSet rs = stmt.executeQuery("show tables");
        //依次输出结果集内容
        while (rs.next()) {
            tableNames.add(rs.getString(1));
        }
        rs.close();
        stmt.close();
        if (connection != null) {
            connection.close();
        }
        return tableNames;
    }

    public List<TableFile> getColumns(String tableName, String dbName) throws SQLException, ClassNotFoundException {
        List<TableFile> tableFiles = new ArrayList<>();
        Connection connection = getConnect();
        String sql = "SELECT TABLE_CATALOG as tableCatalog, TABLE_SCHEMA as tableSchema, TABLE_NAME as tableName,\n" +
                "        COLUMN_NAME as columnName,ORDINAL_POSITION as ordinalPosition, COLUMN_DEFAULT as columnDefault,\n" +
                "        IS_NULLABLE as isNullable, DATA_TYPE as dataType, CHARACTER_MAXIMUM_LENGTH as characterMaximumLength,\n" +
                "        CHARACTER_OCTET_LENGTH as characterOctetLength, NUMERIC_PRECISION as numericPrecision,NUMERIC_SCALE as numericScale,\n" +
                "        DATETIME_PRECISION as datetimePrecision,CHARACTER_SET_NAME as characterSetName,COLLATION_NAME as collationName,\n" +
                "        COLUMN_TYPE as columnType,COLUMN_KEY as columnKey, extra, privileges, COLUMN_COMMENT as columnComment\n" +
                "        FROM information_schema.COLUMNS\n" +
                "        WHERE TABLE_NAME = ?\n" +
                "        AND TABLE_SCHEMA = ?;";
        PreparedStatement pstmt = connection.prepareStatement(sql);

        pstmt.setString(1, tableName);
        pstmt.setString(2, dbName);
        ResultSet rs = pstmt.executeQuery();
        //依次输出结果集内容
        while (rs.next()) {
            TableFile tableFile = new TableFile();
            tableFile.setTableCatalog(rs.getString("tableCatalog"))
                    .setTableSchema(rs.getString("tableSchema"))
                    .setColumnName(rs.getString("columnName"))
                    .setColumnDefault(rs.getString("columnDefault"))
                    .setIsNullable(rs.getString("isNullable"))
                    .setDataType(rs.getString("dataType"))
                    .setCharacterSetName(rs.getString("characterSetName"))
                    .setCollationName(rs.getString("collationName"))
                    .setColumnType(rs.getString("columnType"))
                    .setColumnKey(rs.getString("columnKey"))
                    .setExtra(rs.getString("extra"))
                    .setPrivileges(rs.getString("privileges"))
                    .setColumnComment(rs.getString("columnComment"));
            tableFiles.add(tableFile);
        }
        rs.close();
        pstmt.close();
        connection.close();
        return tableFiles;
    }


}
