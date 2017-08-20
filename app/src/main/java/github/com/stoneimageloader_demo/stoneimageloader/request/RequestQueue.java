package github.com.stoneimageloader_demo.stoneimageloader.request;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

import github.com.stoneimageloader_demo.stoneimageloader.dispatch.RequestDispatcher;


/**
 * Email  1562363326@qq.com
 * Github https://github.com/skcodestack
 * Created by sk on 2017/8/14
 * Version  1.0
 * Description: 请求队列
 */

public class RequestQueue {

    //优先级阻塞式队列
    private BlockingQueue<BitmapRequest> mRequestBlockingQueue = new PriorityBlockingQueue<>();
    //转发器集合
    private RequestDispatcher[] mDispatchers;
    //原子
    private AtomicInteger mInc = new AtomicInteger();

    //活动线程数
    private int mThreadCount ;
    public RequestQueue(int threadCount){
        this.mThreadCount = threadCount;
    }

    /**
     * 添加请求
     * @param request
     */
    public void put(BitmapRequest request){

        if(mRequestBlockingQueue.contains(request)){
            return;
        }
        try {

            request.setPriorNo(mInc.incrementAndGet());
            mRequestBlockingQueue.put(request);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    /**
     * 获取请求
     * @return
     */
    public BitmapRequest get(){

        BitmapRequest request = null;
        try {
            request = mRequestBlockingQueue.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return request;
    }


    /**
     * 开始请求
     */
    public void start(){
        startDispatchers();
    }

    /**
     * 开启转发器
     */
    private void startDispatchers() {

        mDispatchers = new  RequestDispatcher[mThreadCount];

        for (int i = 0; i < mThreadCount; i++) {

            RequestDispatcher dispatcher = new RequestDispatcher(mRequestBlockingQueue);
            mDispatchers[i] = dispatcher;
            dispatcher.start();

        }

    }

    /**
     * 停止请求
     */
    public void stop(){


    }




}
