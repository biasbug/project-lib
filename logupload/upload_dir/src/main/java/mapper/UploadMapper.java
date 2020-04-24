package mapper;

import org.apache.ibatis.annotations.*;
import pojo.FileUploadInfo;
import pojo.SNCodeInfo;

import java.sql.SQLException;
import java.util.List;

public interface UploadMapper {


    @Insert("insert into file_info values(uuid(),#{snCode},#{uploadTime},#{filePath})")
    public void addFileInfo(FileUploadInfo fileUploadInfo) throws SQLException;

    @Select("select count(*) from sn_info")
    public Integer findSnNum() throws SQLException;

    @Select("select * from sn_info where sn_code = #{sncode}")
    public SNCodeInfo findBySncode(String sncode) throws SQLException;

    @Insert("insert into sn_info(sn_code,num,last_update_time) values(#{snCode},#{num},#{lastUpdateTime})")
    public void addSnInfo(SNCodeInfo snCodeInfo) throws SQLException;

    @Update("update sn_info set num =#{num},last_update_time = #{lastUpdateTime} where sn_code = #{snCode}")
    public void updateSnInfo(SNCodeInfo snCodeInfo) throws SQLException;

    @Select("select * from file_info order by upload_time desc")
    public List<FileUploadInfo> findFileInfoAll() throws SQLException;

    @Select("select * from file_info where sn_code like concat('%',#{sncode},'%') order by upload_time desc")
    public List<FileUploadInfo> findFileInfoBySnCode(String sncode) throws SQLException;

    @Select("SELECT * FROM `sn_info` where sn_code = #{sncode} limit 1")
    public SNCodeInfo findSNCodeInfoById(String sncode);

    @Select("select * from file_info where fid = #{fid} limit 1")
    public FileUploadInfo findFileUploadInfoById(String fid);

    @Select("select * FROM file_info where sn_code = #{sncode} order by upload_time asc limit 100 ")
    public List<FileUploadInfo> findSnFile(String sncode);

    @Select("Select num from `sn_info` where sn_code = #{sncode} limit 1")
    public Integer findSnFileNum(String sncode);


    @Delete("<script>delete from file_info where fid in" +
            "<foreach collection='snFileList' item='snFile' open='(' close=')' separator=','>" +
            " #{snFile.fid}" +
            "</foreach>" +
            "</script>")
    public void delFileInfoBySn(@Param("snFileList") List<FileUploadInfo> snFileList) throws SQLException;

    @Update("update sn_info set num = #{num} where sn_code = #{snCode} ")
    public void updateSnInfoNum(SNCodeInfo snCodeInfo) throws SQLException;

    @Select("select * from file_info where sn_code = #{sncode} limit 1 ")
    public FileUploadInfo findFileUploadInfoOne(String sncode) throws SQLException;

    @Delete("delete from file_info where sn_code = #{sncode}")
    public void delFileUploadInfoAll(String sncode) throws SQLException;

    @Delete("delete from sn_info where sn_code = #{sncode}")
    public void delSnInfoAll(String sncode) throws SQLException;
}
