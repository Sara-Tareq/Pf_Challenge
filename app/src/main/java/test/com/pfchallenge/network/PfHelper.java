package test.com.pfchallenge.network;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;

import test.com.pfchallenge.MainView;
import test.com.pfchallenge.entities.Property;
import test.com.pfchallenge.entities.PropertyFinder;


public class PfHelper {

	private static int pageNum = 0;
	private static final String API_BASE_URL = "https://www.propertyfinder.ae/mobileapi?";
	private static final String PG_PARAM = "page=";
	private static final String ORDER_PARAM = "&order=";

	public static void getPropertiesList(MainView mainView, Context context) {
		PfRequestHandler.getInstance(context).requestPropertiesList(getApiUrl(), getPropertiesListResponseHandler(mainView));
	}

	private static ResponseHandler getPropertiesListResponseHandler(final MainView mainView) {
		return new ResponseHandler() {
			@Override
			public void onSuccess(Object response) {
				ArrayList<Property> properties = ((PropertyFinder) response).getProperties();
				if (properties != null && !properties.isEmpty()) {
					pageNum++;
					mainView.updateList(properties);
				}
			}

			@Override
			public void onError(Exception e) {

			}
		};
	}

	private static String getApiUrl() {
		Log.d("PF","page num  :  " + pageNum);
		return API_BASE_URL.concat(PG_PARAM).concat(String.valueOf(pageNum));
	}
}
