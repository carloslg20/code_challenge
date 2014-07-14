package app.cleon.com.photoexplorer.ui;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import java.util.List;

import app.cleon.com.photoexplorer.PEApplication;
import app.cleon.com.photoexplorer.R;
import app.cleon.com.photoexplorer.api.PhotoUrlUtil;
import app.cleon.com.photoexplorer.bo.Photo;
import app.cleon.com.photoexplorer.bo.PhotoListResponse;

/**
 * Created Carlos Leon
 */
public class PhotoGridFragment extends Fragment implements AdapterView.OnItemClickListener{

    private static final String STATE_ACTIVATED_POSITION = "activated_position";

    private int mActivatedPosition = ListView.INVALID_POSITION;

    private GridView mGridView;
    private View mProgress;
    private List<Photo> mList;
    private GridAdapter mAdapter;
    private Callback mCallback;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // retain this fragment
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_photo_grid, container, false);
        mGridView = (GridView) view.findViewById(android.R.id.list);
        mProgress = view.findViewById(android.R.id.progress);
        mGridView.setOnItemClickListener(this);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (mAdapter == null) {
            requestPhotos();
        } else {
            mGridView.setAdapter(mAdapter);
        }
    }
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        // Activities containing this fragment must implement its callbacks.
        if (!(activity instanceof Callback)) {
            throw new IllegalStateException("Activity must implement fragment's callbacks.");
        }
        mCallback = (Callback) activity;
    }

    private void showProgressBar(boolean visible) {
        mProgress.setVisibility(visible? View.VISIBLE: View.GONE);
        mGridView.setVisibility(visible? View.GONE: View.VISIBLE);
    }

    private Response.Listener<PhotoListResponse> mListener = new Response.Listener<PhotoListResponse>() {
        @Override
        public void onResponse(PhotoListResponse response) {
            showProgressBar(false);
            if (response.getStat().equalsIgnoreCase("ok")) {
                updatedUI(response);
            } else {
                Toast.makeText(getActivity(), "service error", Toast.LENGTH_SHORT).show();
            }

        }
    };

    private Response.ErrorListener mErrorListener = new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            showProgressBar(false);
            Toast.makeText(getActivity(), "service error", Toast.LENGTH_SHORT).show();
        }
    };

    private void updatedUI(PhotoListResponse response) {
        mList = response.getPhotos().getPhoto();
        mAdapter = new GridAdapter(getActivity(), mList);
        mGridView.setAdapter(mAdapter);
    }

    private void requestPhotos() {
        showProgressBar(true);
        PEApplication.getApi().getPhotoList(mListener,mErrorListener);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Photo photo = (Photo) parent.getItemAtPosition(position);
        if (mCallback != null) {
            mCallback.onItemSelected(photo);
        }
    }


    private static class ViewHolder {
        private NetworkImageView imageView;
        private TextView title;
    }

    private class GridAdapter extends BaseAdapter {
        private List<Photo> mList;
        private LayoutInflater mLayoutInflater;
        private ImageLoader mImageLoader;
        private GridAdapter(Context context, List<Photo> list) {
            mList = list;
            mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            mImageLoader = PEApplication.getImageLoader();
        }

        public int getCount() {
            return mList.size();
        }

        public Photo getItem(int position) {
            return mList.get(position);
        }

        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                holder = new ViewHolder();
                convertView = mLayoutInflater.inflate(R.layout.gridview_cell, parent, false);
                holder.imageView = (NetworkImageView)convertView.findViewById(android.R.id.icon);
                holder.title = (TextView) convertView.findViewById(android.R.id.title);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            Photo photo = getItem(position);
            holder.imageView.setImageUrl(PhotoUrlUtil.getSmallPhotoUrl(photo), mImageLoader);
            holder.imageView.setErrorImageResId(R.drawable.holder);
            holder.title.setText(photo.getTitle());
            return convertView;
        }
    }

}
