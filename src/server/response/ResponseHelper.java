package server.response;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import server.Constants;
import server.Resource;
import server.WebServer;
import server.configuration.MimeTypes;
import server.request.Request;

public class ResponseHelper {
	
	public static String getHeaderString(HashMap<String, String> headers) {
		StringBuilder sb = new StringBuilder();
		for (String key : headers.keySet()) {
			sb.append(key + ": " + headers.get(key) + "\n");
		}
		sb.append("\n");
		return sb.toString();
	}

	public static boolean createFile() {
		File file = new File(Constants.TEST_FILE_PATH);
		if (file.mkdirs()) {
			try {
				file.createNewFile();
				return true;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	public static boolean deleteFile() {
		File file = new File(Constants.TEST_FILE_PATH);
		if (file.exists()) {
			file.delete();
			return true;
		}
		return false;
	}

	public static String getContentType(Request request) {
		String requestContentType = request.getContentType();
		if (requestContentType != null)
			return requestContentType;

		MimeTypes mimeTypes = WebServer.getMimeTypes();
		String mimeType = mimeTypes.lookup(getUriExtension(request.getAbsolutePath()));
		return mimeType != null ? mimeType : Constants.DEFAULT_MIME_TYPE;
	}

	public static String getUriExtension(String uri) {
		if (uri != null) {
			String[] content = uri.split("\\.");
			int length = content.length;
			if (length > 1)
				return content[length - 1];
		}
		return null;
	}
	
	public static boolean needsUpdate(Request request, Resource resource) {
		if (request.containsIsModified()) {
			String filePath = request.getAbsolutePath();
			if (filePath == null) {
				filePath = resource.getAbsolutePath(false);
				request.setAbsolutePath(filePath);
			}
			File file = new File(filePath);
			long fileTime = getTimeMilliseconds(getDate(file.lastModified()));
			long headerTime = getTimeMilliseconds(request.getLastModified());
			return fileTime != headerTime;
		}
		return true;
	}
	
	public static String getDate(long time) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		return dateFormat.format(time);
	}
	
	public static long getTimeMilliseconds(String date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date d;
		try {
			d = sdf.parse(date);
			return d.getTime();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return 0;
	}


}
