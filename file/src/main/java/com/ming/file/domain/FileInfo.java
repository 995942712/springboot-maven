package com.ming.file.domain;

import lombok.Getter;
import lombok.Setter;

/**
 * 文件管理
 */
@Getter
@Setter
public class FileInfo {

    private Integer id;//id
    private String name;//文件名称
    private String oldName;//旧文件名称
    private String size;//文件大小
    private String suffix;//文件后缀
    private String path;//相对路径
    private String url;//绝对路径

    public FileInfo() {

    }

    public FileInfo(String name, String oldName, String size, String suffix, String path, String url) {
        this.name = name;
        this.oldName = oldName;
        this.size = size;
        this.suffix = suffix;
        this.path = path;
        this.url = url;
    }

}