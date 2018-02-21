package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import server.configuration.Htaccess;
import server.configuration.Htpassword;
import server.configuration.HttpdConf;
import server.configuration.MimeTypes;



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
	
	int clientNum = 0;
	
	public static void main(String[] args) {
		WebServer webServer = new WebServer();
		webServer.start();
	}
	
	public void start() {
		
		httpdConf = new HttpdConf(Constants.HTTPD_CONF_LOCATION);
		httpdConf.load();
		
		mimeTypes = new MimeTypes(Constants.MIME_TYPE_LOCATION);
		mimeTypes.load();
		
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
			Socket client = null;
			while(true) {
				client = socket.accept();
				clientNum++;
				ServerLogger.print("Connected to  Client: "+ clientNum );
				new Thread(new Worker(client, httpdConf, mimeTypes)).start();
			}
			
		}
		catch(IOException e)
		{
			
		}
		
		
	}

	public static HttpdConf getHttpdConf() {
		return httpdConf;
	}

	public static Htaccess getHtaccess() {
		return htaccess;
	}

	public static MimeTypes getMimeTypes() {
		return mimeTypes;
	}

	public static Htpassword getHtpassword() {
		return htpassword;
	}

	
	
	
}
