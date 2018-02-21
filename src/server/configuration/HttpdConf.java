package server.configuration;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

import server.Constants;

/**
 * 
 * @author shalaka
 *
 */
public class HttpdConf {
	
	File httpdFile = null;

	private String serverRoot = null;
	private String documentRoot = null;
	private int listen = 8080;
	private String logFile = null;
	private HashMap<String, String> alias = new HashMap<String, String>();
	private HashMap<String, String> scriptAlias = new HashMap<String, String>();
	private String accessFileName = null;
	private String directoryIndex = null;
	
	public HttpdConf(String fileName) {
		httpdFile = new File(fileName);
	}
	
	public void load() {
		Scanner scanner = null;

		try {
			scanner = new Scanner(httpdFile);
	         scanner.useDelimiter("\\r?\\n");
	        while(scanner.hasNextLine()) {
	        	String content = scanner.nextLine();
	        	content.trim();
	        	String[] config = content.split("\\s");
	        	String key = config[0].trim();
	        	String value = config[1];
	        	value = value.replaceAll("\"", "").trim();
	        	String value1 = null;
	        	if(config.length > 2) {
	        		 value1 = config[2];
		        		value1 = value1.replaceAll("\"", "").trim();
	        	}
	        	populateHttpdConf(key, value, value1);
	        }
		}
		catch(FileNotFoundException ex) {
			System.out.println("HttpdConf file not found "+ex);
		}
		finally{
			if (scanner != null) {
	            scanner.close();
	        }
		}
	}

	private void populateHttpdConf(String key, String value, String value1) {
		switch (key) {
		case Constants.SERVER_ROOT:
			serverRoot = value;
			break;
		case Constants.DOCUMENT_ROOT:
			documentRoot = value;
			break;
		case Constants.LISTEN:
			listen = Integer.parseInt(value);
			break;
		case Constants.LOG_FILE:
			logFile = value;
			break;
		case Constants.SCRIPT_ALIAS:
			scriptAlias.put(value, value1);
			break;
		case Constants.ALIAS:
			alias.put(value, value1);
			break;
		case Constants.ACCESS_FILE_NAME:
			accessFileName = value;
			break;
		case Constants.DIRECTORY_INDEX:
			directoryIndex = value;
			break;
		default:
			System.out.println("Invalid Selection");
			break;
		}
	}

	public String getServerRoot() {
		return serverRoot;
	}

	public String getDocumentRoot() {
		return documentRoot;
	}

	public int getListen() {
		return listen;
	}

	public String getLogFile() {
		return logFile;
	}

	public HashMap<String, String> getAlias() {
		return alias;
	}

	public HashMap<String, String> getScriptAlias() {
		return scriptAlias;
	}

	public String getAccessFileName() {
		return accessFileName;
	}

	public String getDirectoryIndex() {
		return directoryIndex;
	}

}
