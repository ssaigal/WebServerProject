package server.response;

import server.Constants;
import server.Resource;
import server.request.Request;

public class NotFound extends Response {

	public NotFound(Request request, Resource resource) {
		super(request, resource);
	}

	@Override
	public int getCode() {
		return 404;
	}

	@Override
	public String getReasonPhrase() {
		return responseCodes.get(404);
	}

	@Override
	public String getHeaders() {
		headers.put(Constants.CONTENT_LENGTH, String.valueOf(getBody().length()));
		return ResponseHelper.getHeaderString(headers);
	}

	@Override
	public String getBody() {
		return "<h1>404</h1><h2>Not Found</h2>";
	}
}
