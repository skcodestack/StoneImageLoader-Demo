package github.com.stoneimageloader_demo.stoneimageloader.utils;

import android.util.Log;

/**
 * Email  1562363326@qq.com
 * Github https://github.com/skcodestack
 * Created by sk on 2017/8/17
 * Version  1.0
 * Description:
 */

public class FileUtils {

    public static String getFilePerfix(String filename){

        if(filename.contains(".")){
            int i = filename.lastIndexOf(".");
            String substring = filename.substring(i);
            Log.e("FileUtils","===>"+substring);
            return substring;

        }
        return "";

    }
}
