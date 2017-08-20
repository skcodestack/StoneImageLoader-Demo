package github.com.stoneimageloader_demo.stoneimageloader.policy;

import github.com.stoneimageloader_demo.stoneimageloader.request.BitmapRequest;

/**
 * Email  1562363326@qq.com
 * Github https://github.com/skcodestack
 * Created by sk on 2017/8/14
 * Version  1.0
 * Description:
 */

public class SerialPolicy implements LoaderPolicy {


    @Override
    public int compare(BitmapRequest o1, BitmapRequest o2) {
        return o1.getPriorNo() - o2.getPriorNo();
    }
}
