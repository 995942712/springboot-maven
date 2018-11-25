package com.ming.file.domain;

/**
 * 文件管理
 */
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOldName() {
        return oldName;
    }

    public void setOldName(String oldName) {
        this.oldName = oldName;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "FileInfo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", oldName='" + oldName + '\'' +
                ", size='" + size + '\'' +
                ", suffix='" + suffix + '\'' +
                ", path='" + path + '\'' +
                ", url='" + url + '\'' +
                '}';
    }

}