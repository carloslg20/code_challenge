package app.cleon.com.photoexplorer.bo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * Created by Carlos Leon
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class PhotoPage {

    private int mPage;
    private int mPages;
    private int mPerpage;
    private int mTotal;
    private List<Photo> mPhoto;

    public int getPage() {
        return mPage;
    }

    public void setPage(int page) {
        mPage = page;
    }

    public int getPages() {
        return mPages;
    }

    public void setPages(int pages) {
        mPages = pages;
    }

    public int getPerpage() {
        return mPerpage;
    }

    public void setPerpage(int perpage) {
        mPerpage = perpage;
    }

    public int getTotal() {
        return mTotal;
    }

    public void setTotal(int total) {
        mTotal = total;
    }

    public List<Photo> getPhoto() {
        return mPhoto;
    }

    public void setPhoto(List<Photo> photo) {
        mPhoto = photo;
    }
}
