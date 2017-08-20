package github.com.stoneimageloader_demo.stoneimageloader.loader;

import java.util.HashMap;

/**
 * Email  1562363326@qq.com
 * Github https://github.com/skcodestack
 * Created by sk on 2017/8/15
 * Version  1.0
 * Description:
 */

public class LoaderManager {

    private HashMap<String,Loader> mLoaders = new HashMap<>();

    private static LoaderManager mInstance = new LoaderManager();
    private LoaderManager(){
        register("http",new UrlLoader());
        register("https",new UrlLoader());
        register("file",new LocalLoader());
    }

    /**
     * 获取实例
     * @return
     */
    public static LoaderManager getInstance(){
        return mInstance;
    }

    private void register(String schema, Loader loader) {

        mLoaders.put(schema,loader);
    }

    public Loader getLoader(String schmea){
        if(!mLoaders.containsKey(schmea)){
            return new NullLoader();
        }
        return  mLoaders.get(schmea);
    }
}
