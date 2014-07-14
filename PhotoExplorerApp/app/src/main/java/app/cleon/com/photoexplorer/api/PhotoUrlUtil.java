package app.cleon.com.photoexplorer.api;

import app.cleon.com.photoexplorer.bo.Photo;

/**
 * Created by Carlos Leon
 */
public class PhotoUrlUtil {
    public static final String PHOTO_URL = "https://farm%d.staticflickr.com/%s/%s_%s_%s.jpg";

    public static String getSmallPhotoUrl(Photo photo) {
        return String.format(PHOTO_URL, photo.getFarm(), photo.getServer(),
                photo.getId(), photo.getSecret(), "q");
    }

    public static String getLargePhotoUrl(Photo photo) {
        return String.format(PHOTO_URL, photo.getFarm(), photo.getServer(),
                photo.getId(), photo.getSecret(), "z");
    }

    public static String getMediumPhotoUrl(Photo photo) {
        return String.format(PHOTO_URL, photo.getFarm(), photo.getServer(),
                photo.getId(), photo.getSecret(), "c");
    }
}
