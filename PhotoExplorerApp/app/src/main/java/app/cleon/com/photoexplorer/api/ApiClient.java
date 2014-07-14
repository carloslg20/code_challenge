package app.cleon.com.photoexplorer.api;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.fasterxml.jackson.databind.ObjectMapper;

import app.cleon.com.photoexplorer.bo.PhotoListResponse;

public class ApiClient {
    private static ApiClient sInstance;

    private ObjectMapper mMapper;
    private RequestQueue mQueue;

    public static ApiClient getInstance() {
        if (sInstance == null) {
            sInstance = new ApiClient();
        }
        return sInstance;
    }

    public void init(Context context, ObjectMapper mapper) {
        mQueue = Volley.newRequestQueue(context);
        mMapper = mapper;
    }

    public RequestQueue getRequestQueque() {
        return mQueue;
    }

    public int getPhotoList(Response.Listener<PhotoListResponse> listener,
                            Response.ErrorListener errorListener) {
        PhotoListRequest request = PhotoListRequest.newInstance(listener, errorListener);
        addToRequestQueue(request);
        return request.getSequence();
    }

    private <T> void addToRequestQueue(Request<T> request) {
        mQueue.add(request);
    }

}
