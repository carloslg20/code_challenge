package app.cleon.com.photoexplorer.bo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by Carlos Leon
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class PhotoListResponse {

    private PhotoPage mPhotos;
    private String mStat;

    public PhotoPage getPhotos() {
        return mPhotos;
    }

    public void setPhotos(PhotoPage photos) {
        mPhotos = photos;
    }

    public String getStat() {
        return mStat;
    }

    public void setStat(String stat) {
        mStat = stat;
    }
}
