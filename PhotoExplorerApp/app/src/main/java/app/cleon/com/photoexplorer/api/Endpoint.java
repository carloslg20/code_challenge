package app.cleon.com.photoexplorer.api;

/**
 * Created by Carlos Leon
 * Class that hold the endpoints
 */
public class Endpoint {
    public static final String SERVER = " https://api.flickr.com/services/rest/";
    public static final String PHOTO_LIST = "?method=flickr.interestingness.getList&api_key=%s&per_page=20&page=1&format=json&nojsoncallback=1";
}
