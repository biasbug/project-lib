package service;

import org.springframework.web.multipart.MultipartFile;
import pojo.FileUploadInfo;

import java.sql.SQLException;
import java.util.List;

public interface FileUploadService {
    public void saveFile(MultipartFile uploadfile,String sncode,String uploadPath) throws Exception;


    public List<FileUploadInfo> findFileInfoByPage(Integer page,Integer pageSize) throws Exception;

    public List<FileUploadInfo> findFileInfoByPage(Integer page,Integer pageSize,String sncode) throws Exception;

    public void delSnAll(String sncode) throws Exception;
}
