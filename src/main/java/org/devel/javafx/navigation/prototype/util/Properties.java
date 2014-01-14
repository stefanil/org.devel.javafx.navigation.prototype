/**
 * 
 */
package org.devel.javafx.navigation.prototype.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.Proxy;
import java.net.ProxySelector;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author stefan.illgen
 * 
 */
public class Properties extends java.util.Properties {

	public static final String PROXY_CONF_PATH = "./proxy.conf";
	private static final String HTTP_PROXY_HOST_KEY = "http.proxyHost";
	private static final String HTTP_PROXY_PORT_KEY = "http.proxyPort";
	private static final String HTTP_PROXY_USER_KEY = "http.proxyUser";
	private static final String HTTP_PROXY_PASSWORD_KEY = "http.proxyPassword";
	private static final long serialVersionUID = -8717482151558907451L;

	private static Logger logger;

	/**
	 * Instantiates the logger.
	 */
	public Properties() {
		logger = Logger.getLogger(getClass().getName());
	}

	/**
	 * Loads the file input stream under the given {@link #PROXY_CONF_PATH}.
	 */
	public synchronized void load() {
		try {
			super.load(new FileInputStream(PROXY_CONF_PATH));

			// load all properties at once
			// System.setProperties(this);

			// default config
			System.setProperty("java.net.preferIPv4Stack", "true");
			System.setProperty("http.proxySet=true", "true");
			System.setProperty("java.net.useSystemProxies", "true");
			
			// proxy addressing
			System.setProperty(HTTP_PROXY_HOST_KEY,
					(String) get(HTTP_PROXY_HOST_KEY));
			System.setProperty(HTTP_PROXY_PORT_KEY,
					(String) get(HTTP_PROXY_PORT_KEY));
			
			// proxy auth
			try {
				List<Proxy> proxies = ProxySelector.getDefault().select(
						new URI("http://www.google.com"));
				
				// ignoring multiple proxies to simplify code snippet
				final Proxy proxy = proxies.get(0);
				// handle only non direct proxies
				if (proxy.type() != Proxy.Type.DIRECT) {
					System.setProperty(HTTP_PROXY_USER_KEY,
							(String) get(HTTP_PROXY_USER_KEY));
					System.setProperty(HTTP_PROXY_PASSWORD_KEY,
							(String) get(HTTP_PROXY_PASSWORD_KEY));
				}
				
			} catch (URISyntaxException e) {
				e.printStackTrace();
			}
			
		} catch (IOException e) {
			logger.log(Level.SEVERE, e.getMessage(), e);
		}
	}

}
