package github.com.stoneimageloader_demo.stoneimageloader.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * Email  1562363326@qq.com
 * Github https://github.com/skcodestack
 * Created by sk on 2017/8/15
 * Version  1.0
 * Description:
 */

public abstract class BitmapDecoder {

    public Bitmap decodeBitmap(int width,int height){

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        decodeBitmap(options);


        caculateScaleSize(options,width,height);


        return decodeBitmap(options);
    }

    public void caculateScaleSize(BitmapFactory.Options options, int width, int height){
        int outHeight = options.outHeight;
        int outWidth = options.outWidth;

        int inSampleSize = 1;

        if(outHeight > height || outWidth > width){

            int widthSample = Math.round((float)outWidth/(float) width);
            int heightSample = Math.round((float)outHeight/(float) height);

            inSampleSize = Math.max(widthSample, heightSample);

        }

        options.inSampleSize = inSampleSize;
        options.inPreferredConfig = Bitmap.Config.RGB_565;
        options.inJustDecodeBounds = false;
        options.inPurgeable = true;
        options.inInputShareable = true;
    }

    public abstract Bitmap decodeBitmap(BitmapFactory.Options options) ;

}
