package github.com.stoneimageloader_demo.stoneimageloader;

import android.graphics.Bitmap;
import android.widget.ImageView;

import github.com.stoneimageloader_demo.stoneimageloader.config.DisplayConfig;
import github.com.stoneimageloader_demo.stoneimageloader.config.ImageLoaderConfig;
import github.com.stoneimageloader_demo.stoneimageloader.request.BitmapRequest;
import github.com.stoneimageloader_demo.stoneimageloader.request.RequestQueue;


/**
 * Email  1562363326@qq.com
 * Github https://github.com/skcodestack
 * Created by sk on 2017/8/14
 * Version  1.0
 * Description:
 */

public class StoneImageLoader {

    //配置
    private ImageLoaderConfig mImageLoaderConfig;

    //请求队列
    private RequestQueue mRequestQueue;

    //单列
    private  volatile  static StoneImageLoader mInstance;

    private StoneImageLoader(){

    }

    private StoneImageLoader(ImageLoaderConfig config){
        if(config == null){
            throw  new IllegalArgumentException("config is not null pointer");
        }

        this.mImageLoaderConfig  = config;
        mRequestQueue = new RequestQueue(this.mImageLoaderConfig.getmThreadCount());
        mRequestQueue.start();
    }

    /**
     * DCL
     * @param config
     * @return
     */
    public  static StoneImageLoader getInstance(ImageLoaderConfig config){
        if(mInstance == null){
            synchronized (StoneImageLoader.class){
                if(mInstance == null){
                    mInstance = new StoneImageLoader(config);
                }
            }

        }
        return mInstance;
    }

    public  static StoneImageLoader getInstance(){
        if(mInstance == null){
            throw new UnsupportedOperationException("not init object");
        }
        return mInstance;
    }

    /**
     * 显示图片
     * @param imageView
     * @param url http https  file
     */
    public void displayImage(ImageView imageView,String url){


        displayImage(imageView,url,null,null);

    }

    /**
     * 显示图片
     * @param imageView
     * @param url
     * @param displayConfig
     * @param loaderImageListener
     */
    public void displayImage(ImageView imageView, String url, DisplayConfig displayConfig, ILoaderImageListener loaderImageListener){

        BitmapRequest bitmapRequest = new BitmapRequest(imageView,url,displayConfig,loaderImageListener);
        //添加到请求队列
        mRequestQueue.put(bitmapRequest);

    }


    /**
     * 加载图片监听
     */
    public static interface  ILoaderImageListener{
        /**
         * 图片下载完成
         * @param imageView
         * @param bitmap
         * @param url
         */
        void onComplete(ImageView imageView, Bitmap bitmap, String url);


    }


    public ImageLoaderConfig getConfig() {
        return this.mImageLoaderConfig;
    }
}
