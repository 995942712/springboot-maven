package com.ming.file.utils;

import org.springframework.web.multipart.MultipartFile;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 文件管理工具类
 */
public class FileUtil {

    /**
     * 判断文件夹是否存在
     * @param file
     */
    public static void mkDir(File file) throws Exception {
        if (!file.getParentFile().exists()) {
            //创建目录
            boolean b = file.getParentFile().mkdirs();
            if ( !b ) {
                throw new IOException("无法创建目录");
            }
        }
    }

    public static void newFile(MultipartFile file, File saveFile){
        try {

            BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(saveFile));
            out.write(file.getBytes());
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}