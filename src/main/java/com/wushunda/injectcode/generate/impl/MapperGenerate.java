package com.wushunda.injectcode.generate.impl;

import com.wushunda.injectcode.tableFile.TableFile;
import com.wushunda.injectcode.enums.ColumnKey;
import com.wushunda.injectcode.generate.Generate;
import com.wushunda.injectcode.generate.crudMethod;

import static com.wushunda.injectcode.util.Util.firstCapitalLineToHump;
import static com.wushunda.injectcode.util.Util.lineToHump;

/**
 * @author wsd
 */
public class MapperGenerate implements Generate, crudMethod {

    private static final String COMMA = ",";
    private static final String AND = "AND";

    private final EntityGenerate entityGenerate;

    private String primaryKey;

    /**
     * 默认dao存的包
     */
    private String daoPackageName = "dao";
    /**
     * 类名称
     */
    private final String className;
    /**
     * 类变量名
     */
    private final String file;


    public MapperGenerate(EntityGenerate entityGenerate) {
        this.entityGenerate = entityGenerate;
        initiPrimaryKey();
        this.className = firstCapitalLineToHump(entityGenerate.getTableName());
        this.file = lineToHump(entityGenerate.getTableName());
    }

    public MapperGenerate(EntityGenerate entityGenerate, String daoPackageName) {
        this.entityGenerate = entityGenerate;
        this.daoPackageName = daoPackageName;
        initiPrimaryKey();
        this.className = firstCapitalLineToHump(entityGenerate.getTableName());
        this.file = lineToHump(entityGenerate.getTableName());
    }

    @Override
    public String create() {
        return String.format("    <insert id=\"create%s\" useGeneratedKeys=\"true\" keyProperty=\"%s\">\n" +
                "        INSERT INTO %s (%s) \n" +
                "        VALUES(%s)\n" +
                "    </insert>", className, lineToHump(primaryKey), entityGenerate.getTableName(), getColumn(), getValues());
    }

    @Override
    public String createBatch() {
        return String.format("    <insert id=\"create%sBatch\" >\n" +
                "        INSERT INTO %s (%s) \n" +
                "        VALUES\n" +
                "        <foreach collection =\"list\" item=\"%s\" separator =\",\">\n" +
                "            (%s)\n" +
                "        </foreach >\n" +
                "    </insert>", className, entityGenerate.getTableName(), getColumn(),file,getValues());
    }

    @Override
    public String retrieve() {
        return String.format("    <select id=\"get%sById\" resultType=\"%s.%s.%s\">\n" +
                        "        SELECT %s\n" +
                        "        FROM %s\n" +
                        "        WHERE %s = #{%s}\n" +
                        "    </select>", className, entityGenerate.getPackageName(), entityGenerate.getEntityPackageName(), className,
                getColumn(), entityGenerate.getTableName(), primaryKey, file);
    }

    @Override
    public String retrieveBatch() {
        return String.format("    <select id=\"get%sByIdBatch\" resultType=\"%s.%s.%s\">\n" +
                        "        SELECT %s\n" +
                        "        FROM %s\n" +
                        "        WHERE %s IN \n" +
                        "        <foreach collection=\"list\" open=\"(\" close=\")\" item=\"%s\" separator=\",\">\n" +
                        "            #{%s}\n" +
                        "        </foreach>\n" +
                        "    </select>", className, entityGenerate.getPackageName(), entityGenerate.getEntityPackageName(), className,
                getColumn(), entityGenerate.getTableName(), primaryKey, file,file);
    }

    @Override
    public String retrievePagination() {
        return String.format("    <select id=\"get%sPagination\" resultType=\"%s.%s.%s\">\n" +
                        "        SELECT %s\n" +
                        "        FROM %s\n" +
                        "        LIMIT #{start},#{limit}\n"+
                        "    </select>", className, entityGenerate.getPackageName(), entityGenerate.getEntityPackageName(), className,
                getColumn(), entityGenerate.getTableName());
    }

    @Override
    public String count() {
        return String.format("    <select id=\"get%sCount\" resultType=\"java.lang.Integer\">\n" +
                "        SELECT COUNT(*)\n" +
                "        FROM %s  \n" +
                "    </select>",className,entityGenerate.getTableName());
    }

    @Override
    public String update() {
        return String.format("    <update id=\"update%sById\">\n" +
                        "        UPDATE %s \n" +
                        "        %s\n" +
                        "        WHERE %s = #{%s}\n" +
                        "    </update>", className, entityGenerate.getTableName(),
                getUpdateColumn(), primaryKey, file + "." + lineToHump(primaryKey));
    }

    @Override
    public String updateBatch() {
        return String.format("    <update id=\"update%sByIdBatch\">\n" +
                        "        UPDATE %s \n" +
                        "        %s\n" +
                        "        WHERE %s IN \n" +
                        "        <foreach collection=\"list\" open=\"(\" close=\")\" item=\"%s\" separator=\",\">\n" +
                        "            #{%s}\n" +
                        "        </foreach>\n" +
                        "    </update>", className, entityGenerate.getTableName(),
                getUpdateColumn(), primaryKey, file ,file);
    }

    @Override
    public String deleteById() {
        return String.format("    <delete id=\"delete%sById\">\n" +
                        "        DELETE FROM %s\n" +
                        "        WHERE %s=#{%s}\n" +
                        "    </delete>", className,
                entityGenerate.getTableName(), primaryKey, file);
    }

    @Override
    public String deleteByIdBatch() {
        return String.format("    <delete id=\"delete%sByIdBatch\">\n" +
                        "        DELETE FROM %s\n" +
                        "        WHERE %s IN\n" +
                        "        <foreach collection=\"list\" open=\"(\" close=\")\" item=\"%s\" separator=\",\">\n" +
                        "            #{%s}\n" +
                        "        </foreach>\n" +
                        "    </delete>", className,
                entityGenerate.getTableName(), primaryKey, file,file);
    }

    @Override
    public String delete() {
        return String.format("    <delete id=\"delete%s\">\n" +
                "        DELETE FROM %s\n" +
                "        WHERE %s\n" +
                "    </delete>", className, entityGenerate.getTableName(), getDeleteCondition());
    }

    @Override
    public String build() {
        StringBuilder str = new StringBuilder();
        String head = String.format("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n" +
                "<!DOCTYPE mapper PUBLIC \"-//mybatis.org//DTD Mapper 3.0//EN\" \"http://mybatis.org/dtd/mybatis-3-mapper.dtd\">\n" +
                "\n" +
                "<mapper namespace=\"%s.%s.%s\">", entityGenerate.getPackageName(), daoPackageName, className + "Dao");
        str.append(head).append("\n")
                .append(create()).append("\n\n")
                .append(createBatch()).append("\n\n")
                .append(retrieve()).append("\n\n")
                .append(count()).append("\n\n")
                .append(retrieveBatch()).append("\n\n")
                .append(retrievePagination()).append("\n\n")
                .append(update()).append("\n\n")
                .append(updateBatch()).append("\n\n")
                .append(deleteById()).append("\n\n")
                .append(deleteByIdBatch()).append("\n\n")
                .append(delete()).append("\n")
                .append("</mapper>");
        return str.toString();
    }

    @Override
    public String tableName() {
        return this.entityGenerate.tableName();
    }

    /**
     * 获取主键
     */
    private void initiPrimaryKey() {
        for (TableFile tableFile : entityGenerate.getTableFiles()) {
            if (ColumnKey.PRI.getKey().equals(tableFile.getColumnKey())) {
                primaryKey = tableFile.getColumnName();
                break;
            }
        }
    }

    /**
     * 插入查询的列名
     *
     * @return
     */
    private String getColumn() {
        StringBuilder str = new StringBuilder();
        for (TableFile tableFile : entityGenerate.getTableFiles()) {
            str.append(lineToHump(tableFile.getColumnName())).append(",");
        }
        if (str.length() > 0 && COMMA.equals(String.valueOf(str.charAt(str.length() - 1)))) {
            str.deleteCharAt(str.length() - 1);
        }
        return str.toString();
    }

    /**
     * 更新时的列内容
     *
     * @return
     */
    private String getUpdateColumn() {
        StringBuilder str = new StringBuilder("<set>\n");
        for (TableFile tableFile : entityGenerate.getTableFiles()) {
            str.append("            <if ").
                    append(String.format("test=\"%s.%s != null and %s.%s != ''\"",file,lineToHump(tableFile.getColumnName()),file,lineToHump(tableFile.getColumnName())))
                    .append(">\n");
            str.append("            ").append(tableFile.getColumnName()).append(" = ")
                    .append("#{").append(file)
                    .append(".").append(lineToHump(tableFile.getColumnName())).append("}").append(",\n");
            str.append("            </if>\n");
        }
        str.append("        </set>");
        return str.toString();
    }

    private String getValues() {
        StringBuilder str = new StringBuilder();
        for (TableFile tableFile : entityGenerate.getTableFiles()) {
            str.append("#{").append(file).append(".").append(lineToHump(tableFile.getColumnName())).append("}").append(",");
        }
        if (str.length() > 0 && COMMA.equals(String.valueOf(str.charAt(str.length() - 1)))) {
            str.deleteCharAt(str.length() - 1);
        }
        return str.toString();
    }

    private String getDeleteCondition() {
        StringBuilder str = new StringBuilder();
        for (TableFile tableFile : entityGenerate.getTableFiles()) {
            str.append(" ").append(tableFile.getColumnName()).append(" = ")
                    .append("#{").append(file).append(".")
                    .append(lineToHump(tableFile.getColumnName())).append("}\n").append("               and");
        }
        if (str.length() >= AND.length() && AND.equals(str.substring(str.length() - AND.length(), str.length()))) {
            str.delete(str.length() - AND.length(), str.length());
        }
        return str.toString();
    }
}
