package com.ming.file.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ming.file.domain.FileInfo;
import com.ming.file.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * 文件上传下载
 */
@Controller
@RequestMapping("/file")
public class FileController {

    @Autowired
    private FileService fileService;

    /**
     * 文件列表
     * @param request
     * @return
     */
    @RequestMapping("/index")
    public String index(HttpServletRequest request){
        List<FileInfo> list = this.fileService.findAll();
        request.setAttribute("list", list);
        return "index";
    }

    /**
     * 跳到上传页面
     * @return
     */
    @RequestMapping("/fileUp")
    public String fileUp(){
        return "fileUp";
    }

    /**
     * 文件上传
     * @param file
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String upload(@RequestParam("file") MultipartFile file, HttpServletRequest request){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy" + File.separator + "MM" + File.separator + "dd" + File.separator);
        if (!file.isEmpty()) {
            //相对路径
            String host = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + File.separator + "upload" + File.separator + dateFormat.format(new Date());
            //绝对路径
            String url = ClassUtils.getDefaultClassLoader().getResource("").getPath() + "static" + File.separator + "upload" + File.separator + dateFormat.format(new Date());
            System.out.println("相对路径： " + host);
            System.out.println("绝对路径： " + url);
            //文件上传
            String str = this.fileService.upload(file, host, url);
            //request.setAttribute("json", str);
            return str;
        } else {
            JSONObject json = new JSONObject();
            json.put("msg", "上传失败，因为文件为空.");
            json.put("path", "");
            json.put("code", 0);
            //request.setAttribute("json", json.toJSONString());
            return json.toJSONString();
        }
        //return "redirect:/file/index";
    }

    /**
     * 多文件上传
     * @param files
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/uploads", method = RequestMethod.POST)
    public String uploads(@RequestParam("file") MultipartFile[] files, HttpServletRequest request){
        JSONArray jsonArray = new JSONArray();
        for (MultipartFile file : files) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy" + File.separator + "MM" + File.separator + "dd" + File.separator);
            if (!file.isEmpty()) {
                //相对路径
                String host = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + File.separator + "upload" + File.separator + dateFormat.format(new Date());
                //绝对路径
                String url = ClassUtils.getDefaultClassLoader().getResource("").getPath() + "static" + File.separator + "upload" + File.separator + dateFormat.format(new Date());
                System.out.println("相对路径： " + host);
                System.out.println("绝对路径： " + url);
                //文件上传
                String str = this.fileService.upload(file, host, url);
                jsonArray.add(str);
            } else {
                JSONObject json = new JSONObject();
                json.put("msg", "上传失败，因为文件为空.");
                json.put("path", "");
                json.put("code", 0);
                jsonArray.add(json.toJSONString());
            }
        }
        //request.setAttribute("json", jsonArray.toJSONString());
        //return "redirect:/file/index";
        return jsonArray.toJSONString();
    }

    /**
     * 文件下载
     * @param request
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/download")
    public String download(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("id");
        FileInfo fileInfo = this.fileService.findById(Integer.valueOf(id));
        //文件下载
        String str = this.fileService.download(fileInfo.getUrl(), fileInfo.getName(), fileInfo.getOldName(), response);
        return str;
    }

    /**
     * 多文件下载
     * @param request
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/downloads")
    public String downloads(HttpServletRequest request, HttpServletResponse response) {
        return "";
    }

    /**
     * 多文件打包下载
     * @param request
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/downloadZip")
    public String downloadZip(HttpServletRequest request, HttpServletResponse response) {
        return "";
    }

}