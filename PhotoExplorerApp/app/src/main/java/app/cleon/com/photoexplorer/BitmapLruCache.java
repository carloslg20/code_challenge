package app.cleon.com.photoexplorer;

import android.app.ActivityManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.util.LruCache;

import com.android.volley.toolbox.ImageLoader;

public class BitmapLruCache extends LruCache<String, Bitmap> implements
        ImageLoader.ImageCache {

    private static BitmapLruCache instance;

    public static int getDefaultLruCacheSize() {
        ActivityManager activityManager = (ActivityManager) PEApplication
                .getInstance().getSystemService(Context.ACTIVITY_SERVICE);
        int memoryClassBytes = activityManager.getMemoryClass() * 1024 * 1024;
        return memoryClassBytes / 6;
    }

    public static BitmapLruCache getInstance() {
        if (instance == null) {
            instance = new BitmapLruCache();
        }
        return instance;
    }

    public BitmapLruCache() {
        this(getDefaultLruCacheSize());
    }

    public BitmapLruCache(int sizeInKiloBytes) {
        super(sizeInKiloBytes);
    }

    @Override
    protected int sizeOf(String key, Bitmap value) {
        return value.getRowBytes() * value.getHeight();
    }

    @Override
    public Bitmap getBitmap(String url) {
        return get(url);
    }

    @Override
    public void putBitmap(String url, Bitmap bitmap) {
        put(url, bitmap);
    }

    public boolean contains(String key) {
        return get(key) == null ? false : true;
    }

    public void clearCache() {
        evictAll();
    }
}
