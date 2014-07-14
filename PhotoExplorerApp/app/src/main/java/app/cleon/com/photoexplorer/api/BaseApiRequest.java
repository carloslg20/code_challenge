package app.cleon.com.photoexplorer.api;

import android.util.Log;

import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonRequest;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import app.cleon.com.photoexplorer.PEApplication;

public abstract class BaseApiRequest<T> extends JsonRequest<T> {

    private static final String TAG = "BaseApiRequest";

    protected ObjectMapper mMapper;

    static private String buildRequestUrl(String endpoint) {
        return new StringBuilder(Endpoint.SERVER).append(endpoint).toString();
    }

    protected BaseApiRequest(int method, String params, String requestBody) {
        super(method, buildRequestUrl(params), requestBody, null, null);
        mMapper = PEApplication.getInstance().getMapper();
    }

    protected BaseApiRequest(int method, String params, String requestBody,
                             Response.Listener<T> listener, Response.ErrorListener errorListener) {
        super(method, buildRequestUrl(params), requestBody, listener, errorListener);
        mMapper = PEApplication.getInstance().getMapper();
    }


    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {
        try {
            String jsonString = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
            Log.i(TAG, getUrl() + " response :" + jsonString);
            T result = mMapper.readValue(jsonString, getJavaType());
            return Response.success(result, HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            Log.e(TAG, e.getMessage());
            return Response.error(new VolleyError(e));
        } catch (JsonMappingException e) {
            Log.e(TAG, e.getMessage());
            return Response.error(new VolleyError(e));
        } catch (JsonParseException e) {
            Log.e(TAG, e.getMessage());
            return Response.error(new VolleyError(e));
        } catch (IOException e) {
            Log.e(TAG, e.getMessage());
            return Response.error(new VolleyError(e));
        }
    }

    protected abstract JavaType getJavaType();

}
