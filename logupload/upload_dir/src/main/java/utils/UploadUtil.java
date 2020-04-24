package utils;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.UUID;

public class UploadUtil {
    //获取根路径下directoryName文件夹的绝对路径
    public static String getRealPath(HttpServletRequest request,String directoryName){
        String realPath = request.getSession().getServletContext().getRealPath(directoryName);
        File file = new File(realPath);
        if(!file.exists()){
            file.mkdirs();
        }
        return realPath;
    }

    //返回fileName与UUID字符串拼接的唯一字符串
    public static String getUUIDName(String fileName){
        String uuidName = UUID.randomUUID().toString().replaceAll("-", "");
        return uuidName+"-"+fileName;
    }

    //返回UUID字符串
    public static String getUUIDName(){
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}
