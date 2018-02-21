package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;

import conf.HttpdConf;
import conf.MimeTypes;
import server.request.Request;
import server.response.Response;
import server.response.ResponseFactory;

/**
 * 
 * @author shalaka
 *
 */
public class Worker extends Thread{
	
	Socket client;
	HttpdConf config;
	MimeTypes mimes;
	
	public HashMap<String, String> default_headers = new HashMap<String, String>();	
	
	public Worker(Socket socket, HttpdConf config, MimeTypes mimes ) {
		this.client = socket;
		this.config = config;
		this.mimes = mimes;
	}
	
@Override
public void run(){
       
    		PrintWriter out = null;
    		BufferedReader in = null;
    		OutputStream outputStream = null;

    		try {
    			outputStream = client.getOutputStream();
    			out = new PrintWriter(outputStream, true);
    			in = new BufferedReader(new InputStreamReader(client.getInputStream()));
    			Request request = new Request(in);
    			Resource resource = null;
    			if (request.isRequestValid()) {
    				ServerLogger.print("Request from client: " + request.printRequest());
    				resource = new Resource(request.getUri(), config);
    			}

    			ResponseFactory responseFactory = new ResponseFactory();
    			Response response = responseFactory.getResponse(request, resource);
    			if (response.isFile()) {
    				String headers = response.getStatusLine() + response.getHeaders();
    				outputStream.write(headers.getBytes());
    				outputStream.write(response.getData());
    				outputStream.flush();
    				ServerLogger.print("Response from client: "  + headers);
    			} else {
    				String responseStr = response.writeString();
    				ServerLogger.print("Response from client: " + responseStr);
    				out.print(responseStr);
    				out.flush();
    			}
    		} catch (IOException e) {
    			e.printStackTrace();
    		} finally {
    			try {
    				out.close();
    				outputStream.close();
    				in.close();
    				client.close();
    			} catch (IOException e) {
    				e.printStackTrace();
    			}
    		}
    	
    }

}
