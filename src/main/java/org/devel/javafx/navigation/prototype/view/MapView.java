package org.devel.javafx.navigation.prototype.view;

import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.net.Proxy;
import java.net.ProxySelector;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker;
import javafx.concurrent.Worker.State;
import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import org.devel.javafx.navigation.prototype.Configuration;
import org.devel.javafx.navigation.prototype.viewmodel.MapViewModel;

import de.saxsys.jfx.mvvm.base.view.View;

/**
 * @author stefan.illgen
 * 
 */
public class MapView extends View<MapViewModel> {

	@FXML
	private BorderPane mapView;

	@FXML
	private WebView mapWebView;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		// get the web engine
		WebEngine webEngine = mapWebView.getEngine();

		// set up proxy and load monitors ..
		proxyCheck();
		loadMonitors(webEngine);

		// load url into the engine
		final URL urlGoogleMaps = getClass().getResource(
				Configuration.GOOGLEMAPS_HTML);
		webEngine.load(urlGoogleMaps.toExternalForm());
	}

	private void loadMonitors(WebEngine webEngine) {

		final Worker<Void> loadWorker = webEngine.getLoadWorker();
		// monitor state
		loadWorker.stateProperty().addListener(
				new ChangeListener<Worker.State>() {
					public void changed(ObservableValue<? extends State> ov,
							State oldValue, State newValue) {
						System.err.printf("State changed, old: %s, new: %s%n",
								oldValue, newValue);
					}
				});
		// monitor exceptions 
		loadWorker.exceptionProperty().addListener(
				new ChangeListener<Throwable>() {
					public void changed(
							ObservableValue<? extends Throwable> ov,
							Throwable oldValue, Throwable newValue) {
						System.err.printf(
								"Exception changed, old: %s, new: %s%n",
								oldValue, newValue);
					}
				});

	}

	/*
	 * 
	 */
	private void proxyCheck() {

		System.setProperty("java.net.preferIPv4Stack", "true");
		System.setProperty("http.proxySet=true", "true");
		System.setProperty("java.net.useSystemProxies=true", "true");
		
//		new Properties().load()
		
		System.setProperty("http.proxyHost", "http://AERO.saxsys.de");
		System.setProperty("http.proxyPort", "8080");

		try {
			List<Proxy> proxies = ProxySelector.getDefault().select(
					new URI("http://www.google.com"));
			// ignoring multiple proxies to simplify code snippet
			final Proxy proxy = proxies.get(0); 
			if (proxy.type() != Proxy.Type.DIRECT) {
				System.setProperty("http.proxyUser", "stefan.illgen");
				System.setProperty("http.proxyPassword", "52RpJdcu!@0");
			}
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}

	}

}
