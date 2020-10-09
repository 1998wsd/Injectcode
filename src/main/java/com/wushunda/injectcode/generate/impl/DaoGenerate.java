package com.wushunda.injectcode.generate.impl;

import com.wushunda.injectcode.generate.JavaGenerate;
import com.wushunda.injectcode.generate.crudMethod;

import static com.wushunda.injectcode.util.Util.firstCapitalLineToHump;
import static com.wushunda.injectcode.util.Util.lineToHump;

/**
 * @author wsd
 */
public class DaoGenerate implements JavaGenerate, crudMethod {

    /**
     * 对应的实体类
     */
    private final EntityGenerate entityGenerate;
    /**
     * 表名称
     */
    private final String tableName;
    /**
     * 类名称
     */
    private final String className;
    /**
     * 类变量名
     */
    private final String variableName;

    /**
     * dao包名
     */
    private String daoPackageName = "dao";

    public DaoGenerate(EntityGenerate entityGenerate) {
        this.entityGenerate = entityGenerate;
        this.tableName = entityGenerate.getTableName();
        this.className = firstCapitalLineToHump(entityGenerate.getTableName());
        this.variableName = lineToHump(entityGenerate.getTableName());
    }

    public DaoGenerate(EntityGenerate entityGenerate, String daoPackageName) {
        this.entityGenerate = entityGenerate;
        this.tableName = entityGenerate.getTableName();
        this.className = firstCapitalLineToHump(entityGenerate.getTableName());
        this.variableName = lineToHump(entityGenerate.getTableName());
        this.daoPackageName = daoPackageName;
    }

    @Override
    public String getPackage() {
        return String.format("package %s.%s;\n", entityGenerate.getPackageName(), daoPackageName);
    }

    @Override
    public String getImport() {
        return String.format("import %s.%s.%s;\n" +
                        "import org.apache.ibatis.annotations.Mapper;\n" +
                        "import org.apache.ibatis.annotations.Param;\n" +
                        "import org.springframework.stereotype.Repository;\n" +
                        "\nimport java.util.List;\n",
                entityGenerate.getPackageName(), entityGenerate.getEntityPackageName(), className);
    }

    @Override
    public String getMethod() {
        return create() + "\n" +
                retrieve() + "\n" +
                update() + "\n" +
                deleteById() + "\n" +
                delete() + "\n";
    }

    @Override
    public String getFile() {
        return "\n";
    }

    @Override
    public String build() {
        StringBuilder result = new StringBuilder();

        String interfaceStr = String.format("@Mapper\n@Repository\npublic interface %sDao {", className);

        result.append(getPackage()).append("\n")
                .append(getImport()).append("\n")
                .append(interfaceStr).append("\n")
                .append(create()).append("\n")
                .append(createBatch()).append("\n")
                .append(retrieve()).append("\n")
                .append(retrieveBatch()).append("\n")
                .append(update()).append("\n")
                .append(updateBatch()).append("\n")
                .append(deleteById()).append("\n")
                .append(deleteByIdBatch()).append("\n")
                .append(delete()).append("\n")
                .append("}");
        return result.toString();
    }

    @Override
    public String tableName() {
        return this.tableName;
    }


    @Override
    public String create() {
        return String.format("    Integer create%s(@Param(\"%s\") %s %s);\n", className, variableName, className, variableName);
    }

    @Override
    public String createBatch() {
        return String.format("    Integer create%sBatch(@Param(\"%sList\") List<%s> %sList);\n", className, variableName, className, variableName);
    }

    @Override
    public String retrieve() {
        return String.format("    %s get%sById(@Param(\"%s\") Integer %sId);\n", className, className, variableName, variableName);
    }

    @Override
    public String retrieveBatch() {
        return String.format("    %s get%sByIdBatch(@Param(\"%sList\") List<Integer> %sIdList);\n", className, className, variableName, variableName);
    }

    @Override
    public String update() {
        return String.format("    Integer update%sById(@Param(\"%s\") %s %s);\n", className, variableName, className, variableName);
    }

    @Override
    public String updateBatch() {
        return String.format("    Integer update%sByIdBatch(@Param(\"%sList\") List<%s> %sList);\n", className, variableName, className, variableName);
    }

    @Override
    public String deleteById() {
        return String.format("    Integer delete%sById(@Param(\"%s\") Integer %sId);\n", className, variableName, variableName);
    }

    @Override
    public String deleteByIdBatch() {
        return String.format("    Integer delete%sByIdBatch(@Param(\"%sList\") List<Integer> %sIdList);\n", className, variableName, variableName);
    }

    @Override
    public String delete() {
        return String.format("    Integer delete%s(@Param(\"%s\") %s %s);\n", className, variableName, className, variableName);
    }

}
