package github.com.stoneimageloader_demo.stoneimageloader.dispatch;

import android.util.Log;

import java.util.concurrent.BlockingQueue;

import github.com.stoneimageloader_demo.stoneimageloader.loader.Loader;
import github.com.stoneimageloader_demo.stoneimageloader.loader.LoaderManager;
import github.com.stoneimageloader_demo.stoneimageloader.request.BitmapRequest;


/**
 * Email  1562363326@qq.com
 * Github https://github.com/skcodestack
 * Created by sk on 2017/8/14
 * Version  1.0
 * Description: 任务转发器
 */

public class RequestDispatcher  extends Thread{

    private static String TAG = "RequestDispatcher";

    //队列引用
    private BlockingQueue<BitmapRequest> mRequestBlockingQueue;


    public RequestDispatcher(BlockingQueue<BitmapRequest> mRequestBlockingQueue) {
        this.mRequestBlockingQueue = mRequestBlockingQueue;
    }


    @Override
    public void run() {

        while (!this.isInterrupted()){

            try {
                BitmapRequest request = mRequestBlockingQueue.take();
                //解析协议
                String schmea = parseSchema(request.getmUrl());

                Loader loader =
                        LoaderManager.getInstance().getLoader(schmea);
                //加载图片
                loader.loadImage(request);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }

    /**
     * 解析 url
     * @param url
     */
    private String parseSchema(String url) {

        if(url.contains("://")){
            return url.split("://")[0];
        }else {

            Log.e(TAG,"暂时不支持这种类型");
        }
        return "";
    }
}
