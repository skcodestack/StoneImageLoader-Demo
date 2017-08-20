package github.com.stoneimageloader_demo.stoneimageloader.loader;

import android.graphics.Bitmap;

import github.com.stoneimageloader_demo.stoneimageloader.request.BitmapRequest;


/**
 * Email  1562363326@qq.com
 * Github https://github.com/skcodestack
 * Created by sk on 2017/8/14
 * Version  1.0
 * Description: 本地加载器
 */

public class NullLoader extends AbstractLoader {

    @Override
    public Bitmap onLoad(BitmapRequest request) {
        return null;
    }
}
