package server;

import java.net.Socket;

import conf.HttpdConf;
import conf.MimeTypes;

/**
 * 
 * @author shalaka
 *
 */
public class Worker {
	
	private Socket socket;
	private HttpdConf config;
	private MimeTypes mimes;
	
	
	public Worker(Socket socket, HttpdConf config, MimeTypes mimes ) {
		run();
	}
	
	public void run() {
		
	}
	
	
	public Socket getSocket() {
		return socket;
	}
	public HttpdConf getConfig() {
		return config;
	}
	public MimeTypes getMimes() {
		return mimes;
	}

}
