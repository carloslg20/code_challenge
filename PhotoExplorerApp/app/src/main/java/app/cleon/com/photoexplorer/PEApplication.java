package app.cleon.com.photoexplorer;

import android.app.Application;

import com.android.volley.toolbox.ImageLoader;
import com.fasterxml.jackson.databind.ObjectMapper;

import app.cleon.com.photoexplorer.api.ApiClient;

/**
 * Created by Carlos Leon
 */
public class PEApplication extends Application {

    private static PEApplication sInstance;

    private static ImageLoader sImageLoader;

    private ApiClient mApi;

    private ObjectMapper mMapper;

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
        mApi = ApiClient.getInstance();
        mApi.init(this, getMapper());
    }

    public static PEApplication getInstance() {
        return sInstance;
    }

    public static ImageLoader getImageLoader() {
        if (sImageLoader == null) {
            sImageLoader = new ImageLoader(sInstance.mApi.getRequestQueque(),
                    BitmapLruCache.getInstance());
        }
        return sImageLoader;
    }

    public static ApiClient getApi() {
        return sInstance.mApi;
    }

    public ObjectMapper getMapper() {
        if (mMapper == null) {
            mMapper = new ObjectMapper();
        }
        return mMapper;
    }
}
