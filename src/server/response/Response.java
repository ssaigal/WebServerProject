package server.response;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import server.Constants;
import server.Resource;
import server.request.Request;

public class Response  {

	int code;
	String reasonPhrase;
	HashMap<String, String> headers = new HashMap<String, String>();
	Request request;
    HashMap<Integer, String> responseCodes = new HashMap<Integer, String>();

	
	public Response(Request request, Resource resource) {
		
		populateStatusCode();
		this.request = request;
	    String date = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime());

		
		headers.put(Constants.DATE, date);
		headers.put(Constants.SERVER, Constants.SERVER_NAME);
		headers.put(Constants.CONTENT_TYPE, ResponseHelper.getContentType(request));
	}

	private void populateStatusCode() {
		responseCodes.put(200, "OK");
		responseCodes.put(201, "Successfully Created");
		responseCodes.put(304, "Not Modified");
		responseCodes.put(400, "Bad Request");
		responseCodes.put(401, "Unauthorized");
		responseCodes.put(403, "Forbidden");
		responseCodes.put(404, "Not Found");
        responseCodes.put(500, "Internal Server Error");
	}

	public String writeString() {
		return getStatusLine() + getHeaders() + getBody();
	}

	public int getCode() {
		return 500;
	}

	public String getReasonPhrase() {
		return responseCodes.get(500);
	}

	public String getHeaders() {
		headers.put(Constants.CONTENT_LENGTH, String.valueOf(getBody().length()));
		return ResponseHelper.getHeaderString(headers);
	}

	public String getBody() {
		return "<h1>500</h1><h2>Internal Server Error</h2>";
	}

	public String getStatusLine() {
		return request.getHttpVersion() + " " + getCode() + " " + getReasonPhrase() + "\n";
	}

	public byte[] getData() {
		return null;
	}

	public boolean isFile() {
		return false;
	}
}
