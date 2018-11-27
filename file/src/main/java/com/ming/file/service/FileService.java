package com.ming.file.service;

import com.ming.file.domain.FileInfo;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.List;

/**
 * 文件管理Service
 */
public interface FileService {

    /**
     * 上传文件
     * @param file
     * @param host
     * @param url
     * @return
     */
    String upload(MultipartFile file, String host, String url);

    /**
     * 下载文件
     * @param path
     * @param oldFileName
     * @param response
     * @return
     */
    String download(String path, String oldFileName, HttpServletResponse response);

    /**
     * 下载文件
     * @param file
     * @param response
     * @param isDelete
     * @return
     */
    void download(File file, HttpServletResponse response, boolean isDelete);

    /**
     * 查询全部文件
     * @return
     */
    List<FileInfo> findAll();

    /**
     * 根据id查询文件
     * @param id
     * @return
     */
    FileInfo findById(Integer id);

    /**
     * 根据name查询文件
     * @param name
     * @return
     */
    FileInfo findByName(String name);

    /**
     * 保存文件
     * @return
     */
    boolean save(FileInfo file);

    /**
     * 更新文件
     * @return
     */
    boolean update(FileInfo file);

    /**
     * 删除文件
     * @return
     */
    boolean delete(Integer id);

}