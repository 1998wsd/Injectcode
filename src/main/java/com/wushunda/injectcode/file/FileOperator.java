package com.wushunda.injectcode.file;

import java.io.File;
import java.io.IOException;

/**
 * @author wsd
 */
public interface FileOperator {

    /**
     * 创建文件
     *
     * @param fileName
     * @return
     */
    File createFile(String fileName) throws IOException;

    /**
     * 指定目录下创建存放文件的目录
     *
     * @return
     */
    String mkdir(String packPath);

    /**
     * 写入文件信息
     *
     * @param dirPath  文件存放目录路径
     * @param date     文件内容
     * @param fileName 文件名
     */
    void writeFile(String dirPath, String date, String fileName) throws IOException;
}
