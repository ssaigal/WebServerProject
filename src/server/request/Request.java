package server.request;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;

import server.Constants;

/**
 * 
 * @author shalaka
 *
 */
public class Request {

	String uri;
	String body;
	String verb;
	String httpVersion;
	HashMap<String, String> headers;
    HashMap<String, Boolean> httpVerbs = new HashMap<String, Boolean>();
    boolean isRequestValid = true;
    String contentType;

	 public Request(BufferedReader in) throws IOException {
		 		 
		 String line = in.readLine();
		 if(line.equals(null)) {
		 return;
		 }
		 
		 parseVerbUriHttpversion(line);
		 populateHttpVerbs();

		 isRequestValid = validateRequest(verb,httpVersion);
		 if (!isRequestValid)
			return;
		
		line = in.readLine();
		parseHeaderLine(line);
		contentType = headers.get(Constants.CONTENT_TYPE);
		
		if (headers.containsKey(Constants.CONTENT_LENGTH)) {
			parseBody(in);
		}
	 }

	 public void parseVerbUriHttpversion(String firstLine) {
		 if (firstLine.equals(null))
		 {
			 isRequestValid = false;
			 return;
		 }
		 String[] content = firstLine.split(" ");
			if (content.length == 3) {
				verb = content[0].trim();
				uri = content[1].trim();
				httpVersion = content[2].trim();
			}
	 }
	 
	public void populateHttpVerbs() {
		httpVerbs.put("PUT", true);
		 httpVerbs.put("GET", true);
		 httpVerbs.put("POST", true);
		 httpVerbs.put("HEAD", true);
		 httpVerbs.put("DELETE", true);
		 httpVerbs.put("OPTIONS",true);
		 httpVerbs.put("PATCH", true);
		 httpVerbs.put("CONNECT", true);
		 httpVerbs.put("TRACE", true);

	}
	
	 
	 public boolean validateRequest(String verb,String httpVersion) {
		 boolean isVerbValid = httpVerbs.containsKey(verb);
		 boolean isHttpVersionValid = httpVersion.equals(Constants.HTTP_VERSION_1_0) || httpVersion.equals(Constants.HTTP_VERSION_1_1);
		 
		 if(!isVerbValid || !isHttpVersionValid)
			 return false;
		 
		 return true;
	 }
	 
	 public void parseHeaderLine(String line) {
	        try {
	            String[] content = line.split(": ");
	            if (content.length == 2) {
	            	if (content[0].trim().equals(Constants.CONTENT_TYPE)) {
	    				String[] type = content[1].trim().split("; ");
	    				headers.put(content[0].trim(), type[0].trim());
	    			} 
	            	else {
	            		headers.put(content[0].trim(), content[1].trim());
	    			}
	            }
	        } catch (Exception e) {
	            System.out.println(e);
	        }
	    }
	 
	 public void parseBody(BufferedReader in) throws IOException {
			int length = Integer.valueOf(headers.get(Constants.CONTENT_LENGTH));
			char[] buffer = new char[length];
			in.read(buffer, 0, length);
			body = new String(buffer, 0, buffer.length);
		}

	public String getUri() {
		return uri;
	}

	public String getBody() {
		return body;
	}

	public String getVerb() {
		return verb;
	}

	public String getHttpVersion() {
		return httpVersion;
	}

	public boolean isRequestValid() {
		return isRequestValid;
	}
	 
}
