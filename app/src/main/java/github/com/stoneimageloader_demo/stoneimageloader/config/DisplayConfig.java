package github.com.stoneimageloader_demo.stoneimageloader.config;

/**
 * Email  1562363326@qq.com
 * Github https://github.com/skcodestack
 * Created by sk on 2017/8/14
 * Version  1.0
 * Description: 显示图片配置
 */

public class DisplayConfig {

    //加载中图片
    private int mLoadingImageRes = -1;
    //加载失败图片
    private int mFaildImageRes = -1;

    public int getmLoadingImageRes() {
        return mLoadingImageRes;
    }

    public void setmLoadingImageRes(int mLoadingImageRes) {
        this.mLoadingImageRes = mLoadingImageRes;
    }

    public int getmFaildImageRes() {
        return mFaildImageRes;
    }

    public void setmFaildImageRes(int mFaildImageRes) {
        this.mFaildImageRes = mFaildImageRes;
    }
}
