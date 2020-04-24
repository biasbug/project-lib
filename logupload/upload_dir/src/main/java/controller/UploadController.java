package controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import mapper.UploadMapper;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.ModelAndView;
import pojo.FileUploadInfo;
import pojo.SNCodeInfo;
import service.FileUploadService;
import utils.UploadUtil;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class UploadController {
    @Autowired
    private FileUploadService fileUploadService;

    @Autowired
    private UploadMapper uploadMapper;

    /**
     * 文件上传请求接口
     * @param uploadfile 上传的文件
     * @param request 请求
     * @return
     * @throws IOException
     */
    @ResponseBody
    @RequestMapping(value = "/put",method = RequestMethod.PUT)
    public String put(MultipartFile uploadfile, HttpServletRequest request) throws IOException {
        //获取SN序列号
        String sncode = request.getParameter("sncode");
        if(sncode==null || "".equals(sncode) || uploadfile == null){
            return "sncode/file is null";
        }
        try{
            Integer snNum = uploadMapper.findSnNum();
            SNCodeInfo snCodeInfo = uploadMapper.findSNCodeInfoById(sncode);
            if(snNum >= 5 && snCodeInfo == null){
                return "upload faile,sn limit";
            }

            //获取路径
            String uploadPath = "upload"+ File.separator+sncode;
            uploadPath = UploadUtil.getRealPath(request, uploadPath);
            fileUploadService.saveFile(uploadfile,sncode,uploadPath);
            return "upload success";
        }catch (Exception e){
            e.printStackTrace();
            return "upload faile";
        }
    }



    @RequestMapping("/findByPage")
    public ModelAndView findByPage(@RequestParam(name = "page",defaultValue = "1")Integer page,@RequestParam(name = "sncode",defaultValue = "")String sncode) throws Exception {
        Integer pageSize = 10 ;
        PageInfo<FileUploadInfo> pageInfo = null;
        ModelAndView modelAndView = new ModelAndView();
        if(sncode == null || "".equals(sncode)){
            Page<FileUploadInfo> startPage = PageHelper.startPage(page, pageSize);
            List<FileUploadInfo> fileInfoList = fileUploadService.findFileInfoByPage(page, pageSize);
            pageInfo = new PageInfo<>(startPage.getResult());
            pageInfo.setList(fileInfoList);
        }else{
            Page<FileUploadInfo> startPage = PageHelper.startPage(page, pageSize);
            List<FileUploadInfo> fileInfoList = fileUploadService.findFileInfoByPage(page, pageSize,sncode);
            pageInfo = new PageInfo<>(startPage.getResult());
            pageInfo.setList(fileInfoList);
        }
        modelAndView.addObject("pageInfo",pageInfo);
        modelAndView.setViewName("fileInfo-list");
        return modelAndView;
    }


    @RequestMapping("/downloadFile")
    public void downloadFile(String fid,HttpServletRequest request, HttpServletResponse response) throws Exception {
        //1. 获取文件的名称和文件路径
        FileUploadInfo fileUploadInfo = uploadMapper.findFileUploadInfoById(fid);
        String filePath = fileUploadInfo.getFilePath();
        String type = filePath.substring(filePath.lastIndexOf("."));
        String filename = filePath.substring(filePath.lastIndexOf(File.separator)+1);

        //2.设置文件投类型
        response.setHeader("Content-Type",type);

        //3. 处理中文文件名
        String agent = request.getHeader("User-Agent");
        if (agent.contains("MSIE")) {
            // IE浏览器
            filename = URLEncoder.encode(filename, "utf-8");
            filename = filename.replace("+", " ");
        } else if (agent.contains("Firefox")) {
            // 火狐浏览器
            Base64.Encoder base64Encoder = Base64.getEncoder();
            filename = "=?utf-8?B?" + base64Encoder.encode(filename.getBytes("utf-8")) + "?=";
            //            jdk9以后删除了BASE64Encoder，官方建议使用Base64.Encoder
            //            BASE64Encoder base64Encoder = new BASE64Encoder();
            //            filename = "=?utf-8?B?" + base64Encoder.encode(filename.getBytes("utf-8")) + "?=";
        } else {
            // 其它浏览器
            filename = URLEncoder.encode(filename, "utf-8");
        }

        // 4. 设置Content-Disposition头，即点击文件时不打开，直接下载
        response.setHeader("Content-Disposition","attachment;filename="+filename);

        //5. 传输文件
        ServletOutputStream outputStream = response.getOutputStream();
        FileInputStream fileInputStream = new FileInputStream(filePath);
        int len = 0;
        byte[] bytes = new byte[1024];
        while((len = fileInputStream.read(bytes))!=-1){
            outputStream.write(bytes,0,len);
        }
        outputStream.close();
        fileInputStream.close();
    }

    @ResponseBody
    @RequestMapping(value = "/delSnAll")
    public String delSnAll(String sncode,String delcode){
        if(delcode== null || !"magic".equals(delcode)){
            return "delete faile";
        }
        if(sncode == null || "".equals(sncode)){
            return "delete faile";
        }

        try {
            fileUploadService.delSnAll(sncode);
        } catch (Exception e) {
            e.printStackTrace();
            return "delete faile";
        }
        return "delete success";
    }


}
