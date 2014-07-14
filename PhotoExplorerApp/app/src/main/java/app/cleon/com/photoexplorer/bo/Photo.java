package app.cleon.com.photoexplorer.bo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by Carlos Leon
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Photo {

    private String mId;
    private String mOwner;
    private String mSecret;
    private String mServer;
    private int mFarm;
    private String mTitle;

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    public String getOwner() {
        return mOwner;
    }

    public void setOwner(String owner) {
        mOwner = owner;
    }

    public String getSecret() {
        return mSecret;
    }

    public void setSecret(String secret) {
        mSecret = secret;
    }

    public String getServer() {
        return mServer;
    }

    public void setServer(String server) {
        mServer = server;
    }

    public int getFarm() {
        return mFarm;
    }

    public void setFarm(int farm) {
        mFarm = farm;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }
}
