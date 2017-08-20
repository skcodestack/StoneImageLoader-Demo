package github.com.stoneimageloader_demo.stoneimageloader.loader;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import github.com.stoneimageloader_demo.stoneimageloader.request.BitmapRequest;
import github.com.stoneimageloader_demo.stoneimageloader.utils.BitmapDecoder;
import github.com.stoneimageloader_demo.stoneimageloader.utils.ImageViewHelper;

/**
 * Email  1562363326@qq.com
 * Github https://github.com/skcodestack
 * Created by sk on 2017/8/14
 * Version  1.0
 * Description: URL加载器
 */

public class UrlLoader extends AbstractLoader {
    @Override
    public Bitmap onLoad(final BitmapRequest request) {

//        HttpURLConnection connection = null;
//        InputStream inputStream = null;
//        try {
//            connection = (HttpURLConnection) new URL(request.getmUrl()).openConnection();
//            //转化成 buffinputstream
//            inputStream = new BufferedInputStream(connection.getInputStream());
//            //做标记
//            inputStream.mark(inputStream.available());
//            final InputStream finalInputStream = inputStream;
//            BitmapDecoder bitmapDecoder = new BitmapDecoder() {
//                @Override
//                public Bitmap decodeBitmap(BitmapFactory.Options options) {
//                    Bitmap bitmap = BitmapFactory.decodeStream(finalInputStream,null,options);
//
//                    if(options.inJustDecodeBounds){
//
//                        try {
//                            finalInputStream.reset();
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//
//                    }else {
//
//                        try {
//                            finalInputStream.close();
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                    }
//
//                    return bitmap;
//
//                }
//            };
//
//            return bitmapDecoder.decodeBitmap(ImageViewHelper.getImageViewWidth(request.getImageView()),ImageViewHelper.getImageViewHeight(request.getImageView()));
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        //先下载  后读取
        downloadImgByUrl(request.getmUrl(),getCache(request.getmImageUrlMD5() ));
        BitmapDecoder decoder=new BitmapDecoder() {
            @Override
            public Bitmap decodeBitmap(BitmapFactory.Options options) {
                return BitmapFactory.decodeFile(getCache(request.getmImageUrlMD5()).getAbsolutePath(),options);

            }
        };
        return decoder.decodeBitmap(ImageViewHelper.getImageViewWidth(request.getImageView())
                ,ImageViewHelper.getImageViewHeight(request.getImageView()));
    }

    public static boolean downloadImgByUrl(String urlStr, File file)
    {
        FileOutputStream fos = null;
        InputStream is = null;
        try
        {

//            if(file.exists()){
//               file.delete();
//            }
//            file.createNewFile();

            URL url = new URL(urlStr);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            is = conn.getInputStream();
            fos = new FileOutputStream(file);
            byte[] buf = new byte[512];
            int len = 0;
            while ((len = is.read(buf)) != -1)
            {
                fos.write(buf, 0, len);
            }
            fos.flush();
            return true;

        } catch (Exception e)
        {
            e.printStackTrace();

            Log.e("downloadImgByUrl","downloadImgByUrl =======> " + e);

        } finally
        {
            try
            {
                if (is != null)
                    is.close();
            } catch (IOException e)
            {
            }

            try
            {
                if (fos != null)
                    fos.close();
            } catch (IOException e)
            {
            }
        }

        return false;

    }
    private File getCache(String unipue)
    {
        File file=new File(Environment.getExternalStorageDirectory(),"ImageLoader");
        if(!file.exists())
        {
            file.mkdirs();
        }
        return new File(file,unipue);
    }
}
