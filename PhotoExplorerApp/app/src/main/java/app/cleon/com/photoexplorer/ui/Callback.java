package app.cleon.com.photoexplorer.ui;

import app.cleon.com.photoexplorer.bo.Photo;

public interface Callback {
    void onItemSelected(Photo photo);
}
