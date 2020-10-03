package com.wushunda.injectcode.file;

import java.io.File;
import java.io.IOException;

/**
 * @author wsd
 */
public abstract class AbstractFileOperator implements FileOperator {

    @Override
    public File createFile(String fileName) throws IOException {
        File file = new File(fileName);
        //判断该文件是否真实存在
        if (!file.exists()) {
            file.createNewFile();
        }
        return file;
    }

    @Override
    public String mkdir(String packPath) {
        File dir = new File(packPath);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        return packPath;
    }

}
