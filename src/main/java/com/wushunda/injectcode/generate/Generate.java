package com.wushunda.injectcode.generate;

public interface Generate {
    /**
     * 输出文件内容
     *
     * @return
     */
    String build();

    /**
     * 对应的表明
     *
     * @return
     */
    String tableName();
}
