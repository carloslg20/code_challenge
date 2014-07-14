package app.cleon.com.photoexplorer.api;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.fasterxml.jackson.databind.JavaType;

import app.cleon.com.photoexplorer.PEConfig;
import app.cleon.com.photoexplorer.bo.PhotoListResponse;

/**
 * Created by Carlos Leon
 */
public class PhotoListRequest extends BaseApiRequest<PhotoListResponse> {

    private Response.Listener mListener;
    private Response.ErrorListener mErrorListener;

    private static String buildParams() {
        return String.format(Endpoint.PHOTO_LIST, PEConfig.API_KEY);
    }

    public static PhotoListRequest newInstance(Response.Listener<PhotoListResponse> listener,
                                               Response.ErrorListener errorListener) {
        return new PhotoListRequest(listener, errorListener);
    }

    protected PhotoListRequest(Response.Listener<PhotoListResponse> listener,
                               Response.ErrorListener errorListener) {
        super(Method.GET, buildParams(), null, listener, errorListener);
        mListener = listener;
        mErrorListener = errorListener;
    }

    @Override
    protected void deliverResponse(PhotoListResponse response) {
        super.deliverResponse(response);
        if (mListener != null) {
            mListener.onResponse(response);
        }
    }

    @Override
    public void deliverError(VolleyError error) {
        super.deliverError(error);
        if (mErrorListener != null) {
            mErrorListener.onErrorResponse(error);
        }
    }

    @Override
    protected JavaType getJavaType() {
        return mMapper.constructType(PhotoListResponse.class);
    }
}
