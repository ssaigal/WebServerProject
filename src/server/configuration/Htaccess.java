package server.configuration;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import server.Constants;

/**
 * 
 * @author shalaka
 *
 */
public class Htaccess {
	
	File htaccessFile = null;
	Scanner scanner = null;
	
	private String userFile;
	private String authType;
	private String authName;
	private String require;
	
	HttpdConf configuration;
	
	public Htaccess(HttpdConf config) {
		configuration = config;
		htaccessFile = new File(Constants.HTACCESS_LOCATION);
	}
	
	public void load() {
		
	
		try {
	         scanner = new Scanner(htaccessFile);
	         scanner.useDelimiter("\\r?\\n");
	        while(scanner.hasNextLine()) 
		        {
		        	String content = scanner.nextLine();
		        	content.trim();
		        	String[] config = content.split("\\s");
		        	String key = config[0].trim();
		        	String value = null;
		        	if(config[1].startsWith("\"")){
		        		config[1] = config[1].replaceAll("\"", "");	
		        	}
		        	value = config[1];
		        	populateHtaccess(key, value);
		        }
			}
		catch(FileNotFoundException ex) {
			System.out.println("Htaccess file not found "+ex);
		}
		finally {
	        if (scanner != null) {
	            scanner.close();
	        }
		
		}
	}
	
	public boolean isAuthorized(String username, String password) {
		return false;
	}

	private void populateHtaccess(String key, String value) {
		switch (key) {
		case Constants.AUTH_USER_FILE:
			userFile = value;
			break;
		case Constants.AUTH_TYPE:
			authType = value;
			break;
		case Constants.AUTH_NAME:
			authName = value;
			break;
		case Constants.REQUIRE:
			require = value;
			break;
		default:
			System.out.println("Invalid Selection");
			break;
		}
	}

	public String getUserFile() {
		return userFile;
	}

	public String getAuthType() {
		return authType;
	}

	public String getAuthName() {
		return authName;
	}

	public String getRequire() {
		return require;
	}

}
