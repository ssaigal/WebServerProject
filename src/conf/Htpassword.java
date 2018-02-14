package conf;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.util.Base64;
import java.util.HashMap;
import java.util.Scanner;

import server.Constants;

/**
 * 
 * @author shalaka
 *
 */
public class Htpassword {
	
	private static HashMap<String, String> users = new HashMap<String, String>();
	
	public void load(String filePath) {
		Scanner scanner = null;
		try {
			File htpassFile = new File(filePath);
	         scanner = new Scanner(htpassFile);
	        scanner.useDelimiter("\\r?\\n");
	        while(scanner.hasNextLine()) 
		        {
	        	String content = scanner.nextLine();
	        	String[] password = content.split(":");
	        	password[1] = password[1].replace("{SHA}", "").trim();
	        	users.put(password[0], password[1]);
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
	
	public boolean isAuthorized(String authInfo) {
		// authInfo is provided in the header received from the client
		// as a Base64 encoded string.
		String userInfo[] = authInfo.split(" ");
		String credentials = new String(Base64.getDecoder().decode(userInfo[1]), Charset.forName("UTF-8"));
		// The string is the key:value pair username:password
		String[] content = credentials.split(":");
		return verifyPassword(content[0], content[1]);
	}

	
	public boolean verifyPassword(String username, String password) {
		if(users.containsKey(username)) {
			return users.get(username).equals(password);
		}
		
		return false;
	}
	
	private String encryptClearPassword( String password ) {
	    // Encrypt the cleartext password (that was decoded from the Base64 String
	    // provided by the client) using the SHA-1 encryption algorithm
	    try {
	      MessageDigest mDigest = MessageDigest.getInstance( "SHA-1" );
	      byte[] result = mDigest.digest( password.getBytes() );

	     return Base64.getEncoder().encodeToString( result );
	    } catch( Exception e ) {
	      return "";
	    }
	  }
	
	public static HashMap<String, String> getUsers() {
		return users;
	}
}
