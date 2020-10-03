package com.wushunda.injectcode;

import com.wushunda.injectcode.entity.TableFile;
import com.wushunda.injectcode.file.JavaFileOperator;
import com.wushunda.injectcode.generate.Generate;
import com.wushunda.injectcode.generate.impl.DaoGenerate;
import com.wushunda.injectcode.generate.impl.EntityGenerate;
import com.wushunda.injectcode.generate.impl.MapperGenerate;
import com.wushunda.injectcode.jdbc.JdbcConnect;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.wushunda.injectcode.util.Util.firstCapitalLineToHump;

/**
 * @author wsd
 */
public class GenerateCode {

    /**
     * 生成全量入口
     * @param packagName 项目包名
     * @param packageAbsolutePath 项目的存放绝对路径（"D:\\IdeaProjects\\demo\\src\\main\\java\\com\\example\\demo"）
     * @param mapperAbsolutePath mapper存放的绝对路径 （"D:\\IdeaProjects\\demo\\src\\main\\resources\\mapper"）
     * @param dbName 数据库名称
     * @param url 数据库连接url
     * @param user 账号
     * @param pwd 密码
     * @param driver 驱动
     */
    public static void run(String packagName, String packageAbsolutePath, String mapperAbsolutePath, String dbName,
                           String url, String user, String pwd, String driver) {

        JdbcConnect jdbcConnect = new JdbcConnect(url, user, pwd, driver);
        List<String> tableNames = null;
        Map<String, List<TableFile>> columnsMap = new HashMap<>();
        try {
            //获取当前库下所有的表名称
            tableNames = jdbcConnect.getTableNames();
            //表名->字段属性
            for (String tableName : tableNames) {
                List<TableFile> tableFiles = jdbcConnect.getColumns(tableName, dbName);
                columnsMap.put(tableName, tableFiles);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        generate(columnsMap, packagName, packageAbsolutePath, mapperAbsolutePath);
    }

    /**
     * 生成指定表格的文件
     * @param packagName 项目包名
     * @param packageAbsolutePath 项目的存放绝对路径（"D:\\IdeaProjects\\demo\\src\\main\\java\\com\\example\\demo"）
     * @param mapperAbsolutePath mapper存放的绝对路径（"D:\\IdeaProjects\\demo\\src\\main\\resources\\mapper"）
     * @param dbName 数据库名称
     * @param url 数据库连接url
     * @param user 账号
     * @param pwd 密码
     * @param driver 驱动
     * @param selectTables 指定的表格
     */
    public static void run(String packagName, String packageAbsolutePath, String mapperAbsolutePath, String dbName,
                           String url, String user, String pwd, String driver, List<String> selectTables) {

        JdbcConnect jdbcConnect = new JdbcConnect(url, user, pwd, driver);
        Map<String, List<TableFile>> columnsMap = new HashMap<>(selectTables.size());
        try {
            //表名->字段属性
            for (String tableName : selectTables) {
                List<TableFile> tableFiles = jdbcConnect.getColumns(tableName, dbName);
                columnsMap.put(tableName, tableFiles);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        generate(columnsMap, packagName, packageAbsolutePath, mapperAbsolutePath);
    }

    public static void generate(Map<String, List<TableFile>> columnsMap, String packagName,
                                String packageAbsolutePath, String mapperAbsolutePath) {
        //一张表对应三个generate对象
        List<Generate> generates = new ArrayList<>();
        JavaFileOperator fileOperator = new JavaFileOperator();
        for (String tableName : columnsMap.keySet()) {
            EntityGenerate entityGenerate = new EntityGenerate(columnsMap.get(tableName), tableName, packagName);
            generates.add(entityGenerate);
            generates.add(new DaoGenerate(entityGenerate));
            generates.add(new MapperGenerate(entityGenerate));
        }

        //生成Java文件或者Mapper文件
        for (Generate generate : generates) {
            String path;
            String fileName;
            if (generate instanceof DaoGenerate) {
                path = packageAbsolutePath + "/dao";
                fileName = firstCapitalLineToHump(generate.tableName()) + "Dao.java";
            } else if (generate instanceof EntityGenerate) {
                path = packageAbsolutePath + "/entity";
                fileName = firstCapitalLineToHump(generate.tableName()) + ".java";
            } else if (generate instanceof MapperGenerate) {
                path = mapperAbsolutePath;
                fileName = firstCapitalLineToHump(generate.tableName()) + "Mapper.xml";
            } else {
                throw new RuntimeException("error");
            }
            fileOperator.writeFile(path, generate.build(), fileName);
        }
    }
}
