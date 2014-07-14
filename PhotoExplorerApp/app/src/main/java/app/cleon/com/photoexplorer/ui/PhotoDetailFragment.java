package app.cleon.com.photoexplorer.ui;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.toolbox.NetworkImageView;

import app.cleon.com.photoexplorer.PEApplication;
import app.cleon.com.photoexplorer.R;

/**
 * Created by Carlos Leon
 */
public class PhotoDetailFragment extends Fragment {
    private static final String EXTRA_URL ="extra_url";
    private NetworkImageView mImageView;
    public static PhotoDetailFragment newInstance(String url) {
        PhotoDetailFragment fragment = new PhotoDetailFragment();
        Bundle arguments = new Bundle();
        arguments.putString(EXTRA_URL, url);
        fragment.setArguments(arguments);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_photo_detail, container, false);
        mImageView = (NetworkImageView) view.findViewById(android.R.id.icon);
        mImageView.setImageUrl(getArguments().getString(EXTRA_URL), PEApplication.getImageLoader());
        return view;
    }
}
