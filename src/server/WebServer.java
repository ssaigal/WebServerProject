package server;

import java.net.ServerSocket;

import conf.Htaccess;
import conf.Htpassword;
import conf.HttpdConf;
import conf.MimeTypes;

/**
 * 
 * @author shalaka
 *
 */
public class WebServer {
	
	ServerSocket socket;	
	
	static HttpdConf httpdConf;
	static Htaccess htaccess;
	static MimeTypes mimeTypes;
	static Htpassword htpassword;
	
	
	public static void main(String[] args) {
		WebServer webServer = new WebServer();
		webServer.start();
	}
	
	public void start() {
		
		mimeTypes = new MimeTypes(Constants.MIME_TYPE_LOCATION);
		mimeTypes.load();
		
		httpdConf = new HttpdConf(Constants.HTTPD_CONF_LOCATION);
		httpdConf.load();
		
		htaccess = new Htaccess(httpdConf);
		htaccess.load();
		
		htpassword = new Htpassword();
		htpassword.load(htaccess.getUserFile());
		
		ServerLogger serverLogger = new ServerLogger();
		serverLogger.init(httpdConf.getLogFile());
		
		ServerLogger.print("Server is initialized successfully");
		ServerLogger.print("Starting Server on port : " + httpdConf.getListen());
		
		try {
			
			socket = new ServerSocket(httpdConf.getListen());
		}
		catch(Exception e)
		{
			
		}
		
		
	}
	
	
}
