package github.com.stoneimageloader_demo.stoneimageloader.config;

import android.support.annotation.IdRes;

import github.com.stoneimageloader_demo.stoneimageloader.cache.BitmapCache;
import github.com.stoneimageloader_demo.stoneimageloader.cache.MemoryCache;
import github.com.stoneimageloader_demo.stoneimageloader.policy.LoaderPolicy;
import github.com.stoneimageloader_demo.stoneimageloader.policy.ReversePolicy;


/**
 * Email  1562363326@qq.com
 * Github https://github.com/skcodestack
 * Created by sk on 2017/8/14
 * Version  1.0
 * Description: 加载框架配置类
 */

public class ImageLoaderConfig {

    //缓存策略
    private BitmapCache mBitmapCache = new MemoryCache();

    //加载策略
    private LoaderPolicy mLoaderPolicy = new ReversePolicy();

    private int  mAvailableProcessors = Runtime.getRuntime().availableProcessors();
    //默认线程数
    private int mThreadCount = mAvailableProcessors;

    //显示图片配置
    private DisplayConfig mDisplayConfig = new DisplayConfig();

    private ImageLoaderConfig(){

    }


    /**
     * 建造者模式
     */
    public static class Builder{

        private ImageLoaderConfig config;

        public Builder(){
            config = new ImageLoaderConfig();
        }

        /**
         * 设置缓存策略
         * @param bitmapCache
         * @return
         */
        public Builder setCachePolicy(BitmapCache bitmapCache){
            if(bitmapCache != null) {
                config.mBitmapCache = bitmapCache;
            }
            return this;
        }

        /**
         * 设置加载策略
         * @param loaderPolicy
         * @return
         */
        public Builder setLoaderPolicy(LoaderPolicy loaderPolicy){
            if(loaderPolicy != null){
                this.config.mLoaderPolicy = loaderPolicy;
            }

            return this;
        }

        /**
         * 设置线程数
         * @param count
         * @return
         */
        public Builder setThreadCount(int count){
            if(count >0 && count < this.config.mAvailableProcessors){
                this.config.mThreadCount = count;
            }

            return this;
        }

        /**
         * 设置加载中显示的图片
         * @param resId
         * @return
         */
        public Builder setLoadingImageRes( int resId){
            DisplayConfig mDisplayConfig = this.config.mDisplayConfig;
            if(mDisplayConfig == null){
                mDisplayConfig = new DisplayConfig();
            }
            mDisplayConfig.setmLoadingImageRes(resId);
            return this;
        }

        /**
         * 设置加载失败显示的图片
         * @param resId
         * @return
         */
        public Builder setFaildImageRes( int resId){
            DisplayConfig mDisplayConfig = this.config.mDisplayConfig;
            if(mDisplayConfig == null){
                mDisplayConfig = new DisplayConfig();
            }
            mDisplayConfig.setmFaildImageRes(resId);
            return this;
        }


        public ImageLoaderConfig build(){

            return config;
        }

    }

    public BitmapCache getmBitmapCache() {
        return mBitmapCache;
    }

    public LoaderPolicy getmLoaderPolicy() {
        return mLoaderPolicy;
    }

    public int getmThreadCount() {
        return mThreadCount;
    }

    public DisplayConfig getmDisplayConfig() {
        return mDisplayConfig;
    }
}
