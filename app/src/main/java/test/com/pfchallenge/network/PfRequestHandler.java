package test.com.pfchallenge.network;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

import test.com.pfchallenge.entities.PropertyFinder;

/**
 * Created by Sara_R on 16/02/2018.
 */

public class PfRequestHandler {

	private static PfRequestHandler pfRequestHandler;
	private static RequestQueue pfRequestQueue;


	private PfRequestHandler() {
	}

	public static PfRequestHandler getInstance(Context context) {
		if (pfRequestHandler == null) {
			pfRequestHandler = new PfRequestHandler();
			initRequestQueue(context);
		}
		return pfRequestHandler;
	}

	private static void initRequestQueue(Context context) {

		if (pfRequestQueue == null) {
			pfRequestQueue = Volley.newRequestQueue(context);
		}
	}

	public void requestPropertiesList(String url,final ResponseHandler responseHandler){

		GsonRequest<PropertyFinder> getPropertiesRequest = new GsonRequest<>(Request.Method.GET, url, PropertyFinder.class, new Response.Listener<PropertyFinder>() {
			@Override
			public void onResponse(PropertyFinder response) {
				responseHandler.onSuccess(response);
			}
		}, new Response.ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError error) {
				responseHandler.onError(error);
			}
		});

		pfRequestQueue.add(getPropertiesRequest);
	}
}
