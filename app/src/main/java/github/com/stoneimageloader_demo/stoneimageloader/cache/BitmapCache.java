package github.com.stoneimageloader_demo.stoneimageloader.cache;

import android.graphics.Bitmap;

import github.com.stoneimageloader_demo.stoneimageloader.request.BitmapRequest;


/**
 * Email  1562363326@qq.com
 * Github https://github.com/skcodestack
 * Created by sk on 2017/8/14
 * Version  1.0
 * Description:
 */

public interface BitmapCache {


    void put(BitmapRequest request, Bitmap bitmap);

    Bitmap get(BitmapRequest request);

    void remove(BitmapRequest request);

}
