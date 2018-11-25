package com.ming.file.service;

import com.alibaba.fastjson.JSONObject;
import com.ming.file.dao.FileDao;
import com.ming.file.domain.FileInfo;
import com.ming.file.utils.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * 文件管理Service实现类
 */
@Service
public class FileServiceImpl implements FileService {

    @Autowired
    private FileDao fileDao;

    @Override
    public String upload(MultipartFile file, String host, String url) {
        JSONObject json = new JSONObject();
        //文件名
        String oldFileName = file.getOriginalFilename();
        //System.out.println("文件名： " + oldFileName);
        //文件后缀名
        String suffix = oldFileName.substring(oldFileName.lastIndexOf("."));
        //System.out.println("文件后缀名： " + suffix);
        //新的文件名
        String fileName = UUID.randomUUID().toString() + suffix;
        //System.out.println("新的文件名： " + fileName);
        //相对路径
        String path = host + fileName;
        System.out.println("path： " + path);
        //绝对路径
        String filePath = url + fileName;
        System.out.println("filePath： " + filePath);
        try {
            File saveFile = new File(filePath);
            //检测是否存在目录
            if (!saveFile.getParentFile().exists()) {
                //创建目录
                boolean b = saveFile.getParentFile().mkdirs();
                if ( !b ) {
                    throw new IOException("无法创建目录");
                }
            }
            //文件写入
            file.transferTo(saveFile);
            String str = oldFileName + "上传成功";
            //保存数据
            FileInfo fileInfo = new FileInfo(fileName, oldFileName, String.valueOf(file.getSize() + "k"), suffix, path, url);
            if (this.save(fileInfo)) {
                str += ",保存成功";
            } else {
                str += ",保存失败";
            }
            json.put("msg", str);
            json.put("path", path);
            json.put("code", 20);
        } catch (Exception e) {
            e.printStackTrace();
            json.put("msg", oldFileName + "上传失败");
            json.put("path", "");
            json.put("code", 0);
        }
        return json.toJSONString();
    }

    @Override
    public String download(String path, String name, String oldFileName, HttpServletResponse response) {
        JSONObject json = new JSONObject();
        FileInputStream fis = null;
        BufferedInputStream bis = null;
        try {
            File file = new File(path + File.separator + name);
            if (file.exists()) {
                response.setContentType("application/force-download");
                response.addHeader("Content-Disposition", "attachment;fileName=" + oldFileName);
                byte[] buffer = new byte[1024];
                fis = new FileInputStream(file);
                bis = new BufferedInputStream(fis);
                OutputStream os = response.getOutputStream();
                int i = bis.read(buffer);
                while (i != -1) {
                    os.write(buffer, 0, i);
                    os.flush();
                    i = bis.read(buffer);
                }
                json.put("msg", oldFileName + "下载成功");
                json.put("path", "");
                json.put("code", 0);
            } else {
                json.put("msg", oldFileName + "下载失败");
                json.put("path", "");
                json.put("code", 0);
            }
        } catch (Exception e) {
            e.printStackTrace();
            json.put("msg", oldFileName + "下载失败");
            json.put("path", "");
            json.put("code", 0);
            return json.toJSONString();
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return json.toJSONString();
    }

    @Override
    public List<FileInfo> findAll() {
        return this.fileDao.findAll();
    }

    @Override
    public FileInfo findById(Integer id) {
        return this.fileDao.findById(id);
    }

    @Override
    public FileInfo findByName(String name) {
        return this.fileDao.findByName(name);
    }

    @Override
    public boolean save(FileInfo file) {
        return this.fileDao.save(file) == 1 ? true : false;
    }

    @Override
    public boolean update(FileInfo file) {
        return this.fileDao.update(file) == 1 ? true : false;
    }

    @Override
    public boolean delete(Integer id) {
        return this.fileDao.delete(id) == 1 ? true : false;
    }

}