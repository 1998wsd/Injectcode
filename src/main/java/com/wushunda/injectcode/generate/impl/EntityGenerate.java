package com.wushunda.injectcode.generate.impl;

import com.wushunda.injectcode.tableFile.TableFile;
import com.wushunda.injectcode.enums.DateTypeMapping;
import com.wushunda.injectcode.enums.DateTypePackageMapping;
import com.wushunda.injectcode.generate.JavaGenerate;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.wushunda.injectcode.util.Util.firstCapitalLineToHump;
import static com.wushunda.injectcode.util.Util.lineToHump;

/**
 * @author shunda wu
 * @date 2020/9/30
 * @description 默认的实体类的生成实现类
 **/
public class EntityGenerate implements JavaGenerate {

    private List<TableFile> tableFiles;

    private String tableName;
    /**
     * 项目所在包名
     */
    private String packageName;
    /**
     * 默认存放entity的包名
     */
    private String entityPackageName = "entity";

    /**
     * 类名称
     */
    private String className;

    public EntityGenerate(List<TableFile> tableFiles, String tableName, String packageName) {
        this.tableFiles = tableFiles;
        this.tableName = tableName;
        this.packageName = packageName;
        this.className = firstCapitalLineToHump(tableName);
    }

    public EntityGenerate(List<TableFile> tableFiles, String tableName, String packageName, String entityPackageName) {
        this.tableFiles = tableFiles;
        this.tableName = tableName;
        this.packageName = packageName;
        this.entityPackageName = entityPackageName;
        this.className = firstCapitalLineToHump(tableName);
    }

    /**
     * page+import+class+file+method
     *
     * @return .java 内容
     */
    @Override
    public String build() {
        StringBuilder result = new StringBuilder();
        String classFile = String.format("public class %s {", className);

        result.append(getPackage()).append("\n")
                .append(getImport()).append("\n")
                .append(classFile).append("\n")
                .append(getFile()).append("\n")
                .append(getMethod()).append("\n")
                .append("}");
        return result.toString();
    }

    @Override
    public String tableName() {
        return this.tableName;
    }

    @Override
    public String getImport() {
        StringBuilder result = new StringBuilder();
        Set<String> imports = new HashSet<>(16);
        for (TableFile tableFile : tableFiles) {
            //得到类型
            String dateType = tableFile.getDataType();
            String packageName = DateTypePackageMapping.getValue(dateType);
            if (packageName != null) {
                imports.add(packageName);
            }
        }
        for (String s : imports) {
            result.append(String.format("import %s;\n", s));
        }
        return result.toString();
    }

    @Override
    public String getMethod() {
        StringBuilder method = new StringBuilder();
        for (TableFile tableFile : tableFiles) {
            //get
            method.append(String.format(
                    "    public %s get%s() {\n" +
                            "        return this.%s;\n" +
                            "    }\n\n", DateTypeMapping.getValue(tableFile.getDataType()),
                    firstCapitalLineToHump(tableFile.getColumnName()),
                    lineToHump(tableFile.getColumnName())));
            //set
            method.append(String.format(
                    "    public void set%s(%s %s) {\n" +
                            "        this.%s = %s;\n" +
                            "    }\n\n", firstCapitalLineToHump(tableFile.getColumnName()),
                    DateTypeMapping.getValue(tableFile.getDataType()), lineToHump(tableFile.getColumnName()),
                    lineToHump(tableFile.getColumnName()), lineToHump(tableFile.getColumnName())));
        }
        return method.toString();
    }

    @Override
    public String getFile() {
        StringBuilder file = new StringBuilder();
        for (TableFile tableFile : tableFiles) {
            file.append(String.format("    /**\n     * %s\n     */\n    private %s %s;\n",
                    tableFile.getColumnComment(), DateTypeMapping.getValue(tableFile.getDataType()), lineToHump(tableFile.getColumnName())));
        }

        return file.toString();
    }

    @Override
    public String getPackage() {
        return String.format("package %s.%s;\n", packageName, entityPackageName);
    }

    public List<TableFile> getTableFiles() {
        return tableFiles;
    }

    public String getTableName() {
        return tableName;
    }

    public String getPackageName() {
        return packageName;
    }

    public String getEntityPackageName() {
        return entityPackageName;
    }
}
