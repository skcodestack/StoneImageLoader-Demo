package github.com.stoneimageloader_demo.stoneimageloader.cache;

import android.content.Context;
import android.graphics.Bitmap;

import github.com.stoneimageloader_demo.stoneimageloader.request.BitmapRequest;


/**
 * Email  1562363326@qq.com
 * Github https://github.com/skcodestack
 * Created by sk on 2017/8/16
 * Version  1.0
 * Description:
 */

public class DoubleCache implements BitmapCache{

    //内存缓存
    private MemoryCache mMemoryCache = new MemoryCache();
    //硬盘缓存
    private DiskCache mDiskCache;

    public DoubleCache(Context context) {
        mDiskCache = DiskCache.getInstance(context);
    }

    @Override
    public void put(BitmapRequest request, Bitmap bitmap) {
        mMemoryCache.put(request, bitmap);
        mDiskCache.put(request, bitmap);
    }

    @Override
    public Bitmap get(BitmapRequest request) {
        Bitmap bitmap = mMemoryCache.get(request);
        if(bitmap == null){
            bitmap = mDiskCache.get(request);
            if(bitmap != null){
                //放入内存，方便再获取
                mMemoryCache.put(request, bitmap);
            }
        }
        return bitmap;
    }

    @Override
    public void remove(BitmapRequest request) {
        mMemoryCache.remove(request);
        mDiskCache.remove(request);
    }


}
