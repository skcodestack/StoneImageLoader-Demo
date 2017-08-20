package github.com.stoneimageloader_demo.stoneimageloader.loader;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;

import java.io.File;

import github.com.stoneimageloader_demo.stoneimageloader.request.BitmapRequest;
import github.com.stoneimageloader_demo.stoneimageloader.utils.BitmapDecoder;
import github.com.stoneimageloader_demo.stoneimageloader.utils.ImageViewHelper;


/**
 * Email  1562363326@qq.com
 * Github https://github.com/skcodestack
 * Created by sk on 2017/8/14
 * Version  1.0
 * Description: 本地加载器
 */

public class LocalLoader extends AbstractLoader {

    @Override
    public Bitmap onLoad(BitmapRequest request) {

        final String path = Uri.parse(request.getmUrl()).getPath();

        File file = new File(path);

        if(!file.exists()){
            return null;
        }

        BitmapDecoder bitmapDecoder = new BitmapDecoder() {

            @Override
            public Bitmap decodeBitmap(BitmapFactory.Options options) {
                return BitmapFactory.decodeFile(path,options);
            }
        };

        return bitmapDecoder.decodeBitmap(ImageViewHelper.getImageViewWidth(request.getImageView()),ImageViewHelper.getImageViewHeight(request.getImageView()));

    }
}
