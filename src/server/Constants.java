package server;

/**
 * 
 * @author shalaka
 *
 */
public class Constants {
	
	public static final String MIME_TYPE_LOCATION = "src/server/conf/mime.types";
	public static final String HTACCESS_LOCATION = "src/server/conf/_.htaccess";
	public static final String HTTPD_CONF_LOCATION = "src/server/conf/httpd.conf";
	public static final String HTPASSWORD_LOCATION = "src/server/conf/_.htpasswd";

	
	public static final String SERVER_ROOT = "ServerRoot";
	public static final String DOCUMENT_ROOT = "DocumentRoot";
	public static final String LISTEN = "Listen";
	public static final String LOG_FILE = "LogFile";
	public static final String SCRIPT_ALIAS = "ScriptAlias";
	public static final String ALIAS = "Alias";
	public static final String ACCESS_FILE_NAME = "AccessFileName";
	public static final String DIRECTORY_INDEX = "DirectoryIndex";
	public static final int DEFAULT_PORT = 8080;
	
	public static final String CONTENT_LENGTH = "Content-Length";
	public static final String CONTENT_TYPE = "Content-Type";
	public static final String HTTP_VERSION_1_0 = "HTTP/1.0";
	public static final String HTTP_VERSION_1_1 = "HTTP/1.1";
	public static final String DATE = "Date";
	public static final String SERVER = "Server";
	public static final String SERVER_NAME = "web-server-alex-shalaka";

	
	public static final String AUTH_USER_FILE = "AuthUserFile";
	public static final String AUTH_TYPE = "AuthType";
	public static final String AUTH_NAME = "AuthName";
	public static final String REQUIRE = "Require";
	
	public static final String LOGGER_NAME = "alex-shalaka-web-server";
	public static final String DEFAULT_FILE = "index.html";
	public static final String DEFAULT_MIME_TYPE = "application/octet-stream";
	public static final String AUTHORIZATION = "Authorization";
	public static final String LAST_MODIFIED = "Last-Modified";
	public static final String IF_MODIFIED_SINCE = "If-Modified-Since";
	
	public static final String WWW_AUTHENTICATE = "WWW-Authenticate";
	public static final String CACHE_CONTROL = "Cache-Control";
	public static final String NO_CACHE = "no-cache";
	public static final String PRAGMA = "Pragma";
	
	public static final String USR_PERL_EXTENSION = "#!/usr/bin/perl";
	public static final String PERL_EXTENSION = "#!/bin/perl";
	public static final String BASH_EXTENSION = "#!/bin/bash";
	public static final String SH_EXTENSION = "#!/bin/sh";

}
