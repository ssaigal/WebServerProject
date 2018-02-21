package server.response;

import server.Constants;
import server.Resource;
import server.request.Request;

public class Forbidden extends Response {

	public Forbidden(Request request, Resource resource) {
		super(request, resource);
	}

	@Override
	public int getCode() {
		return 403;
	}

	@Override
	public String getReasonPhrase() {
		return responseCodes.get(403);
	}

	@Override
	public String getHeaders() {
		headers.put(Constants.CONTENT_LENGTH, String.valueOf(getBody().length()));
		return ResponseHelper.getHeaderString(headers);
	}

	@Override
	public String getBody() {
		return "<h1>403</h1><h2>Forbidden</h2>";
	}
}