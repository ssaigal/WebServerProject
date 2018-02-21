package server.response;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import conf.ConfigurationReader;
import server.Constants;
import server.Resource;
import server.request.Request;

public class OK extends Response {

	Request request;
	Resource resource;
	boolean isFile = false;
	boolean isScript = false;
	String filePath = null;
	String scriptString = null;

	public OK(Request request, Resource resource) {
		super(request, resource);
		this.request = request;
		this.resource = resource;
	}

	public void setFile(boolean value) {
		isFile = value;
	}

	public void setScript(boolean value) {
		isScript = value;
	}

	public void setFilePath(String path) {
		filePath = path;
	}

	public void setScriptString(String value) {
		scriptString = value;
	}

	@Override
	public int getCode() {
		return 200;
	}

	@Override
	public String getReasonPhrase() {
		return responseCodes.get(200);
	}

	@Override
	public String getHeaders() {
		if (isFile) {
			File file = new File(filePath);
			String timeStamp = ResponseHelper.getDate(file.lastModified());
			headers.put(Constants.LAST_MODIFIED, timeStamp);
		}
		headers.put(Constants.CONTENT_LENGTH, String.valueOf(getBody().length()));
		return ResponseHelper.getHeaderString(headers);
	}

	@Override
	public String getBody() {
		if (isFile)
			return ConfigurationReader.readFile(filePath);
		else if (isScript)
			return scriptString;
		else
			return "<h1>200</h1><h2>OK</h2>";
	}

	@Override
	public byte[] getData() {
		if (isFile) {
			try {
				Path path = Paths.get(filePath);
				return Files.readAllBytes(path);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public boolean isFile() {
		return isFile;
	}
}
