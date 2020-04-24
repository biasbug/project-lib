import java.io.File;

public class MyTest {
    public static void main(String[] args) {
        String filePath = "D:\\learningJava\\project-lib\\logupload\\upload_dir\\target\\upload_dir\\upload\\219801ABYM20320E003\\1587695087896-20200422112759_sysinfo.dbg";
        File file = new File(filePath);
        File fileParentDir = new File(file.getParent());
        if(fileParentDir.exists() && fileParentDir.isDirectory()){
            //delete dir and delete sn info
            System.out.println(1111);
        }else{
            //delete faile
            System.out.println(00000);
        }
    }
}
