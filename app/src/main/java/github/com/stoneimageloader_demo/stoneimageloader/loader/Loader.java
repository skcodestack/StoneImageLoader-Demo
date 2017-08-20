package github.com.stoneimageloader_demo.stoneimageloader.loader;


import github.com.stoneimageloader_demo.stoneimageloader.request.BitmapRequest;

/**
 * Email  1562363326@qq.com
 * Github https://github.com/skcodestack
 * Created by sk on 2017/8/14
 * Version  1.0
 * Description: 加载器
 */

public interface Loader {
    /**
     * 加载图片
     * @param request
     */
    void loadImage(BitmapRequest request);

}
