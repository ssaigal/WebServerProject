package server.response;

import server.Constants;
import server.Resource;
import server.request.Request;

public class InternalServerError extends Response {

	public InternalServerError(Request request, Resource resource) {
		super(request, resource);
	}

	@Override
	public int getCode() {
		return 500;
	}

	@Override
	public String getReasonPhrase() {
		return responseCodes.get(500);
	}

	@Override
	public String getHeaders() {
		headers.put(Constants.CONTENT_LENGTH, String.valueOf(getBody().length()));
		return ResponseHelper.getHeaderString(headers);
	}

	@Override
	public String getBody() {
		return "<h1>500</h1><h2>Internal Server Error</h2>";
	}
}
