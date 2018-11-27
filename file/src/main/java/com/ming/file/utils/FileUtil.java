package com.ming.file.utils;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/**
 * 文件管理工具类
 */
public class FileUtil {

    private int k = 1;//定义递归次数变量

    /**
     * ZIP压缩
     * @param zipFileName 压缩后文件路径
     * @param file 压缩目标路径
     * @throws Exception
     */
    private void zip(File file, String zipFileName) throws Exception {
        System.out.println("压缩中...");
        ZipOutputStream out = new ZipOutputStream(new FileOutputStream(zipFileName));
        BufferedOutputStream bo = new BufferedOutputStream(out);
        zip(out, file, file.getName(), bo);
        bo.close();
        out.close();//输出流关闭
        System.out.println("压缩完成");
    }

    private void zip(ZipOutputStream out, File file, String base, BufferedOutputStream bo) throws Exception {
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            if (files.length == 0) {
                out.putNextEntry(new ZipEntry(base + "/"));//创建zip压缩进入点base
                System.out.println(base + "/");
            }
            for (int i = 0; i < files.length; i++) {
                zip(out, files[i], base + "/" + files[i].getName(), bo);//递归遍历子文件夹
            }
            System.out.println("第" + k + "次递归");
            k++;
        } else {
            out.putNextEntry(new ZipEntry(base));//创建zip压缩进入点base
            System.out.println(base);
            FileInputStream in = new FileInputStream(file);
            BufferedInputStream bi = new BufferedInputStream(in);
            int b;
            while ((b = bi.read()) != -1) {
                bo.write(b);//将字节流写入当前zip目录
            }
            bi.close();
            in.close();//输入流关闭
        }
    }

    /**
     * ZIP解压
     *
     * @param zipPath 源zip路径
     * @param path    解压后路径
     */
    public void jieZip(String zipPath, String path) {
        long startTime = System.currentTimeMillis();
        try {
            ZipInputStream Zin = new ZipInputStream(new FileInputStream(zipPath));
            BufferedInputStream Bin = new BufferedInputStream(Zin);
            File file = null;
            ZipEntry entry;
            try {
                while ((entry = Zin.getNextEntry()) != null && !entry.isDirectory()) {
                    file = new File(path, entry.getName());
                    if (!file.exists()) {
                        (new File(file.getParent())).mkdirs();
                    }
                    FileOutputStream out = new FileOutputStream(file);
                    BufferedOutputStream Bout = new BufferedOutputStream(out);
                    int b;
                    while ((b = Bin.read()) != -1) {
                        Bout.write(b);
                    }
                    Bout.close();
                    out.close();
                    System.out.println(file + "解压成功");
                }
                Bin.close();
                Zin.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        long endTime = System.currentTimeMillis();
        System.out.println("耗费时间： " + (endTime - startTime) + " ms");
    }

    //====================================================================================

    public void zip(String inputFileName, String zipFileName) throws Exception {
        zip(zipFileName, new File(inputFileName));
    }

    public void zip(String zipFileName, File inputFile) throws Exception {
        ZipOutputStream out = new ZipOutputStream(new FileOutputStream(zipFileName));
        zip(out, inputFile, "");
        out.close();
    }

    public void zip(ZipOutputStream out, File file, String base) throws Exception {
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            out.putNextEntry(new ZipEntry(base + "/"));
            base = base.length() == 0 ? "" : base + "/";
            for (int i = 0; i < files.length; i++) {
                zip(out, files[i], base + files[i].getName());
            }
        } else {
            out.putNextEntry(new ZipEntry(base));
            FileInputStream in = new FileInputStream(file);
            int b;
            while ((b = in.read()) != -1) {
                out.write(b);
            }
            in.close();
        }
    }

    //====================================================================================

}