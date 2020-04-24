package pojo;

import utils.MyDateUtils;

import java.util.Date;

public class FileUploadInfo {
    private String fid;
    private String snCode;
    private Date uploadTime;
    private String uploadTimeStr;
    private String filePath;
    private String fileName;

    public String getFid() {
        return fid;
    }

    public void setFid(String fid) {
        this.fid = fid;
    }

    public String getSnCode() {
        return snCode;
    }

    public void setSnCode(String snCode) {
        this.snCode = snCode;
    }

    public Date getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Date uploadTime) {
        this.uploadTime = uploadTime;
    }

    public String getUploadTimeStr() {
        return MyDateUtils.dateToString(this.uploadTime);
    }

    public void setUploadTimeStr(String uploadTimeStr) {
        this.uploadTimeStr = uploadTimeStr;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFileName() {
        return filePath.substring(filePath.lastIndexOf("-")+1);
    }
}
