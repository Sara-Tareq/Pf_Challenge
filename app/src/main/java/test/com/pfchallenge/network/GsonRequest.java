package test.com.pfchallenge.network;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * A Custom volley request to use Gson library in parsing the network response
 * **/
public class GsonRequest<T> extends Request<T> {

	private static final String PROTOCOL_CHARSET = "utf-8";
	private static final String PROTOCOL_CONTENT_TYPE =
		String.format("application/json; charset=%s", PROTOCOL_CHARSET);

	private final Gson gson = new GsonBuilder().setLenient().create();
	private final Class<T> clazz;
	private Map<String, String> headers;
	private JSONObject params;
	private final Response.Listener<T> listener;

	/**
	 * Make a request and return a parsed object from JSON.
	 *
	 * @param url   URL of the request to make
	 * @param clazz Relevant class object, for Gson's reflection
	 */
	public GsonRequest(int method, String url, Class<T> clazz, Response.Listener<T> listener, Response.ErrorListener errorListener) {
		super(method, url, errorListener);
		this.clazz = clazz;
		this.listener = listener;
	}

	/**
	 * Make a request and return a parsed object from JSON.
	 *
	 * @param url     URL of the request to make
	 * @param clazz   Relevant class object, for Gson's reflection
	 * @param headers Map of request headers
	 */
	public GsonRequest(int method, String url, Class<T> clazz, Map<String, String> headers,
										 Response.Listener<T> listener, Response.ErrorListener errorListener) {
		super(method, url, errorListener);
		this.clazz = clazz;
		this.headers = headers;
		this.listener = listener;
	}

	/**
	 * Make a request and return a parsed object from JSON.
	 *
	 * @param url    URL of the request to make
	 * @param clazz  Relevant class object, for Gson's reflection
	 * @param params request params
	 */
	public GsonRequest(int method, String url, Class<T> clazz, JSONObject params,
										 Response.Listener<T> listener, Response.ErrorListener errorListener) {
		super(method, url, errorListener);
		this.clazz = clazz;
		this.params = params;
		this.listener = listener;
	}

	/**
	 * Make a request and return a parsed object from JSON.
	 *
	 * @param url     URL of the request to make
	 * @param clazz   Relevant class object, for Gson's reflection
	 * @param headers Map of request headers
	 * @param params  request params
	 */
	public GsonRequest(int method, String url, Class<T> clazz, Map<String, String> headers, JSONObject params,
										 Response.Listener<T> listener, Response.ErrorListener errorListener) {
		super(method, url, errorListener);
		this.clazz = clazz;
		this.headers = headers;
		this.params = params;
		this.listener = listener;
	}

	@Override
	public Map<String, String> getHeaders() throws AuthFailureError {
		return headers != null ? headers : super.getHeaders();
	}

	@Override
	public byte[] getBody() throws AuthFailureError {
		try {
			return params != null ? params.toString().getBytes(PROTOCOL_CHARSET) : null;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public String getBodyContentType() {
		return PROTOCOL_CONTENT_TYPE;
	}

	@Override
	protected void deliverResponse(T response) {
		listener.onResponse(response);
	}

	@Override
	protected Response<T> parseNetworkResponse(NetworkResponse response) {
		if (response != null && response.data != null && !new String(response.data).isEmpty()) {
			try {
				String json = new String(
					response.data,
					HttpHeaderParser.parseCharset(response.headers, PROTOCOL_CHARSET));
				Response<T> requestResponse = Response.success(
					gson.fromJson(json, clazz),
					HttpHeaderParser.parseCacheHeaders(response));
				return requestResponse;
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
				return Response.error(new ParseError(e));
			} catch (JsonSyntaxException e) {
				e.printStackTrace();
				return Response.error(new ParseError(e));
			}
		} else {
			return Response.error(new VolleyError("Empty Response", new Exception()));
		}
	}
}
