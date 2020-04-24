package service.impl;

import mapper.UploadMapper;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pojo.FileUploadInfo;
import pojo.SNCodeInfo;
import service.FileUploadService;
import utils.UploadUtil;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class FileUploadServiceImpl implements FileUploadService {

    @Autowired
    private UploadMapper uploadMapper;

//    线程池
    @Autowired
    ThreadPoolTaskExecutor taskExecutor;

    /**
     * 保存上传的文件
     * @param uploadfile 上传的文件对象
     * @param sncode 上传设备的SN序列号
     * @param uploadPath 要保存的路径
     * @throws Exception
     */
    @Override
    public void saveFile(MultipartFile uploadfile, String sncode,String uploadPath) throws Exception {
        //获取当前日期时间和上传时间
        Date date = new Date();
        long time = date.getTime();
        String uploadTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);


        //1.获取sn上传信息，如果为空、或者距离上次上传的时间间隔小于5分钟则执行上传任务
        SNCodeInfo snCodeInfo = uploadMapper.findBySncode(sncode);
        if(snCodeInfo == null || ((time - snCodeInfo.getLastUpdateTime().getTime()) > 5*60*1000)){
            //获取上传文件名称
            String filename = uploadfile.getOriginalFilename();


            //文件的存储名 日期+文件名
            filename = time + "-" + filename;
            uploadfile.transferTo(new File(uploadPath,filename));

            //更新文件信息到数据库
            FileUploadInfo fileUploadInfo = new FileUploadInfo();
            fileUploadInfo.setSnCode(sncode);
            fileUploadInfo.setFilePath(uploadPath+File.separator+filename);
            fileUploadInfo.setUploadTime(date);
            uploadMapper.addFileInfo(fileUploadInfo);

            //更新sn上传信息到数据库里面
            if(snCodeInfo == null){
                SNCodeInfo snCodeInfo1 = new SNCodeInfo();
                snCodeInfo1.setSnCode(sncode);
                snCodeInfo1.setNum(1);
                snCodeInfo1.setLastUpdateTime(date);
                uploadMapper.addSnInfo(snCodeInfo1);
            }else{
                snCodeInfo.setLastUpdateTime(date);
                snCodeInfo.setNum(snCodeInfo.getNum() + 1);
                uploadMapper.updateSnInfo(snCodeInfo);

                //新线程任务
                //判断该sn号的上传文件数量，超过1000个文件则删除后100个文件
                //查询当前sn号的数量，超过5个，则删除最后一个的信息

                taskExecutor.execute(new Runnable() {
                    @Override
                    public void run() {
                        //如果大于500则执行删除操作
                        Integer snFileNum = uploadMapper.findSnFileNum(sncode);
                        //如果大于500则执行删除操作
                        if (snFileNum >= 500) {
                            List<FileUploadInfo> snFile = uploadMapper.findSnFile(sncode);
                            for (FileUploadInfo uploadInfo : snFile) {
                                File file = new File(uploadInfo.getFilePath());
                                if (file.exists()) {
                                    FileUtils.deleteQuietly(file);
                                }

                            }
                            //删除file_info表的数据
                            try {
                                uploadMapper.delFileInfoBySn(snFile);
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                            //删除sn_info表的num数据,减掉snFile的长度，如果长度比snFileNum大，则直接剪掉snFileNum的长度
                            Integer delNum = snFile.size() > snFileNum ? snFileNum : snFile.size();
                            Integer snNewNum = snFileNum - delNum;

                            SNCodeInfo snCodeInfo1 = new SNCodeInfo();
                            snCodeInfo1.setNum(snNewNum);
                            snCodeInfo1.setSnCode(sncode);
                            try {
                                uploadMapper.updateSnInfoNum(snCodeInfo1);
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
            }
        }else{
            return;
        }

    }


    @Override
    public List<FileUploadInfo> findFileInfoByPage(Integer page,Integer pageSize) throws Exception {
        return uploadMapper.findFileInfoAll();
    }

    @Override
    public List<FileUploadInfo> findFileInfoByPage(Integer page,Integer pageSize,String sncode) throws Exception {
        return uploadMapper.findFileInfoBySnCode(sncode);
    }

    @Override
    public void delSnAll(String sncode) throws SQLException {
        //根据sn号查找文件路径
        FileUploadInfo fileUploadInfoOne = uploadMapper.findFileUploadInfoOne(sncode);

        File file = new File(fileUploadInfoOne.getFilePath());
        File fileParentDir = new File(file.getParent());
        if(fileParentDir.exists() && fileParentDir.isDirectory()){
            try {
                //删除文件夹及下面所有文件
                FileUtils.deleteDirectory(fileParentDir);
                uploadMapper.delFileUploadInfoAll(sncode);
                uploadMapper.delSnInfoAll(sncode);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }else {
            return;
        }
    }


}
