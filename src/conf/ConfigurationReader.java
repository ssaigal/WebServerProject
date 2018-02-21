package conf;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * 
 * @author shalaka
 *
 */
public class ConfigurationReader {
	
	
	public ConfigurationReader(String fileName) {
		
		readFile(fileName);
		
	}
	
	
	public static String readFile(String fileLocation) {
		File file = new File(fileLocation);
		if(!file.exists())
			return null;
		
		String returnStr = null;
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(fileLocation));
			StringBuilder sb = new StringBuilder();
			String line = br.readLine();

			while (line != null) {
				sb.append(line);
				sb.append(System.lineSeparator());
				line = br.readLine();
			}
			returnStr = sb.toString();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return returnStr;
	}

}
