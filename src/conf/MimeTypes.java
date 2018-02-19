package conf;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.HashMap;
import java.util.Scanner;

/**
 * 
 * @author shalaka
 *
 */
public class MimeTypes {
	
	
	HashMap<String, String> mimeTypes = new HashMap<String, String>();
	File mimeFile = null;
	
	public MimeTypes(String fileName) {
		mimeFile = new File(fileName);
	}
	
	public void load() {
		Scanner scanner = null;

		try {
         scanner = new Scanner(mimeFile);
         scanner.useDelimiter("\\r?\\n");
        while(scanner.hasNextLine()) {
        	String line = scanner.nextLine();
        	line.trim();
        	if(!(line.startsWith("#") || line.isEmpty()) && scanner.hasNext()) {
        		String[] types = line.split("\\s+", 2);
        		String mimeType = types[0];
        		if(types.length == 2) {
        			String[] extensions = types[1].split("\\s");
        			for(String extension: extensions) {
        				mimeTypes.put(extension, mimeType);
                		//System.out.println(extension+ " "+ mimeType);

        		    	}
        			}
        		}
        	}
		}
		catch(FileNotFoundException ex) {
			System.out.println("Mime Types file not found "+ex);
		}
		finally {
	        if (scanner != null) {
	            scanner.close();
	        }
		}
	}
	
	public String lookup(String extension) 
	{
		if(!extension.equals(null)) {
			String mimeType = mimeTypes.get(extension);
			return mimeType;
		}
		return null;
	}
}
