package github.com.stoneimageloader_demo.stoneimageloader.cache;

import android.graphics.Bitmap;
import android.util.LruCache;

import github.com.stoneimageloader_demo.stoneimageloader.request.BitmapRequest;


/**
 * Email  1562363326@qq.com
 * Github https://github.com/skcodestack
 * Created by sk on 2017/8/14
 * Version  1.0
 * Description:
 */

public class MemoryCache implements BitmapCache {

    private LruCache<String,Bitmap> mLruCache;
    public MemoryCache()
    {
        int maxSize= (int) (Runtime.getRuntime().maxMemory()/1024/8);
        mLruCache=new LruCache<String,Bitmap>(maxSize)
        {
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getRowBytes()*value.getHeight();
            }
        };
    }
    @Override
    public void put(BitmapRequest request, Bitmap bitmap) {
        mLruCache.put(request.getmImageUrlMD5(),bitmap);
    }

    @Override
    public Bitmap get(BitmapRequest request) {
        return mLruCache.get(request.getmImageUrlMD5());
    }

    @Override
    public void remove(BitmapRequest request) {
        mLruCache.remove(request.getmImageUrlMD5());
    }


}
