package com.wushunda.injectcode.generate;

public interface JavaGenerate extends Generate {

    /**
     * 包名
     *
     * @return
     */
    String getPackage();

    /**
     * import内容
     *
     * @return
     */
    String getImport();

    /**
     * 方法的内容
     *
     * @return
     */
    String getMethod();

    /**
     * 获取属性
     *
     * @return
     */
    String getFile();
}
