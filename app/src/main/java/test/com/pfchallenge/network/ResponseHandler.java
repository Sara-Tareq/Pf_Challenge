package test.com.pfchallenge.network;


public interface ResponseHandler {

	void onSuccess(Object response);

	void onError(Exception e);
}
