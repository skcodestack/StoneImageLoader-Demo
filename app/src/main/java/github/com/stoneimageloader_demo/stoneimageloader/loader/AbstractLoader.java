package github.com.stoneimageloader_demo.stoneimageloader.loader;

import android.graphics.Bitmap;
import android.widget.ImageView;

import github.com.stoneimageloader_demo.stoneimageloader.StoneImageLoader;
import github.com.stoneimageloader_demo.stoneimageloader.cache.BitmapCache;
import github.com.stoneimageloader_demo.stoneimageloader.config.DisplayConfig;
import github.com.stoneimageloader_demo.stoneimageloader.request.BitmapRequest;


/**
 * Email  1562363326@qq.com
 * Github https://github.com/skcodestack
 * Created by sk on 2017/8/14
 * Version  1.0
 * Description:
 */

public abstract class AbstractLoader implements Loader {

    //缓存
    private BitmapCache mBitmapCache = StoneImageLoader.getInstance().getConfig().getmBitmapCache();

    //显示配置
    private DisplayConfig mDisplayConfig =  StoneImageLoader.getInstance().getConfig().getmDisplayConfig();



    @Override
    public void loadImage(BitmapRequest request) {


            Bitmap bitmap =
                    mBitmapCache.get(request);

            if(bitmap == null){

                showLoadingImage(request);

                bitmap = onLoad(request);

                cacheBitmap(request,bitmap);


            }
            deliveryToUIThread(request,bitmap);


    }
    /**
     * 交给主线程显示
     * @param request
     * @param bitmap
     */
    protected void deliveryToUIThread(final BitmapRequest request, final Bitmap bitmap) {
        ImageView imageView = request.getImageView();
        if(imageView!=null)
        {
            imageView.post(new Runnable() {
                @Override
                public void run() {
                    updateImageView(request, bitmap);
                }

            });
        }

    }

    private void updateImageView(final BitmapRequest request, final Bitmap bitmap) {
        ImageView imageView = request.getImageView();
        //加载正常  防止图片错位
        if(bitmap != null && imageView.getTag().equals(request.getmUrl())){
            imageView.setImageBitmap(bitmap);
        }
        //有可能加载失败
        if(bitmap == null && mDisplayConfig!=null&&mDisplayConfig.getmFaildImageRes()!=-1){
            imageView.setImageResource(mDisplayConfig.getmFaildImageRes());
        }
        //监听
        //回调 给圆角图片  特殊图片进行扩展
        if(request.getLoaderImageListener() != null){
            request.getLoaderImageListener().onComplete(imageView, bitmap, request.getmUrl());
        }
    }
    /**
     * 缓存
     * @param request
     * @param bitmap
     */
    private void cacheBitmap(BitmapRequest request, Bitmap bitmap) {
        if(request != null && bitmap != null && mBitmapCache != null){
            synchronized (mBitmapCache) {
                mBitmapCache.put(request, bitmap);
            }
        }
    }

    /**
     * 加载图片
     * @param request
     * @return
     */
    public abstract Bitmap onLoad(BitmapRequest request);

    /**
     * 显示正在加载图片
     * @param request
     */
    private void showLoadingImage(BitmapRequest request) {
        if(mDisplayConfig != null && mDisplayConfig.getmLoadingImageRes() > 0){

            final ImageView imageView = request.getImageView();

            if(imageView != null){

                imageView.post(new Runnable() {
                    @Override
                    public void run() {
                        imageView.setImageResource(mDisplayConfig.getmLoadingImageRes());
                    }
                });

            }

        }

    }

}
