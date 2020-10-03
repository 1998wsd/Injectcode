package com.wushunda.injectcode.file;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;

/**
 * @author wsd
 */
public class JavaFileOperator extends AbstractFileOperator {


    /**
     * 输出文件
     *
     * @param absolutePath 目录的绝对路径
     * @param date         文件内容
     * @param fileName     文件名
     */
    @Override
    public void writeFile(String absolutePath, String date, String fileName) {
        String path = mkdir(absolutePath);
        try {
            File fie = createFile(path + "/" + fileName);
            FileOutputStream fos = new FileOutputStream(fie, true);
            byte[] data = date.getBytes(StandardCharsets.UTF_8);
            fos.write(data);
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
