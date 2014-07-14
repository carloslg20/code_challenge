package app.cleon.com.photoexplorer.ui;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;

import app.cleon.com.photoexplorer.R;
import app.cleon.com.photoexplorer.api.PhotoUrlUtil;
import app.cleon.com.photoexplorer.bo.Photo;

/**
 * Created by Carlos Leon
 */
public class MainActivity extends Activity implements Callback {

    private boolean mTwoPane;

    private PhotoGridFragment mPhotoGridFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // find the retained fragment on activity restarts
        FragmentManager fm = getFragmentManager();
        mPhotoGridFragment = (PhotoGridFragment) fm.findFragmentByTag("grid_tag");


        if (findViewById(R.id.item_detail_container) != null) {
            mTwoPane = true;
        }
    }

    @Override
    public void onItemSelected(Photo photo) {
        String url = PhotoUrlUtil.getMediumPhotoUrl(photo);
        if (mTwoPane) {
            Fragment fragment = PhotoDetailFragment.newInstance(url);
            getFragmentManager().beginTransaction()
                    .replace(R.id.item_detail_container, fragment)
                    .commit();
        } else {
            Intent intent = PhotoDetailActivity.makeIntent(this, url);
            startActivity(intent);
        }
    }
}
