package github.com.stoneimageloader_demo.stoneimageloader.request;

import android.widget.ImageView;

import java.lang.ref.WeakReference;
import java.util.Comparator;

import github.com.stoneimageloader_demo.stoneimageloader.StoneImageLoader;
import github.com.stoneimageloader_demo.stoneimageloader.config.DisplayConfig;
import github.com.stoneimageloader_demo.stoneimageloader.policy.LoaderPolicy;
import github.com.stoneimageloader_demo.stoneimageloader.utils.MD5Utils;


/**
 * Email  1562363326@qq.com
 * Github https://github.com/skcodestack
 * Created by sk on 2017/8/14
 * Version  1.0
 * Description: 请求单元
 */

public class BitmapRequest implements Comparable<BitmapRequest>{

    //加载策略
    private LoaderPolicy mLoaderPolicy = StoneImageLoader.getInstance().getConfig().getmLoaderPolicy();

    //优先级
    int priorNo;


    //imageview 引用
    private WeakReference<ImageView> mImageViewSoft;
    //图片路径
    private String mUrl;
    //MD5 加密
    private String mImageUrlMD5;
    //图片下载完成监听
    private StoneImageLoader.ILoaderImageListener mLoaderImageListener;
    //显示配置
    private DisplayConfig mDisplayConfig;

    public BitmapRequest(ImageView imageView, String url,
                         DisplayConfig displayConfig , StoneImageLoader.ILoaderImageListener loaderImageListener) {
        this.mUrl = url;
        this.mLoaderImageListener = loaderImageListener;
        this.mImageViewSoft = new WeakReference<ImageView>(imageView);
        imageView.setTag(url);
        this.mImageUrlMD5 = MD5Utils.enCode(url);
        this.mDisplayConfig = displayConfig;
    }


    public int getPriorNo() {
        return priorNo;
    }

    public void setPriorNo(int priorNo) {
        this.priorNo = priorNo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BitmapRequest that = (BitmapRequest) o;

        if (priorNo != that.priorNo) return false;
        return mLoaderPolicy != null ? mLoaderPolicy.equals(that.mLoaderPolicy) : that.mLoaderPolicy == null;

    }

    @Override
    public int hashCode() {
        int result = mLoaderPolicy != null ? mLoaderPolicy.hashCode() : 0;
        result = 31 * result + priorNo;
        return result;
    }


    public ImageView getImageView(){
        if(mImageViewSoft == null){
            return null;
        }
        ImageView imageView = mImageViewSoft.get();
        return imageView;
    }

    public String getmUrl() {
        return mUrl;
    }

    public void setmUrl(String mUrl) {
        this.mUrl = mUrl;
    }

    public String getmImageUrlMD5() {
        return mImageUrlMD5;
    }

    public StoneImageLoader.ILoaderImageListener getLoaderImageListener() {
        return mLoaderImageListener;
    }

    @Override
    public int compareTo(BitmapRequest o) {
        return mLoaderPolicy.compare(this, o);
    }
}
