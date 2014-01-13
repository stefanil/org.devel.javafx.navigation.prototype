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
		loadWorker.stateProperty().addListener(
				new ChangeListener<Worker.State>() {
					public void changed(ObservableValue<? extends State> ov,
							State oldValue, State newValue) {
						System.err.printf("State changed, old: %s, new: %s%n",
								oldValue, newValue);
					}
				});
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
	 * TODO stefan - authenticate with proxy
	 */
	private void proxyCheck() {

		System.setProperty("http.proxySet=true", "true");
		System.setProperty("java.net.useSystemProxies=true", "true");
		System.setProperty("http.proxyHost", "http://AERO.saxsys.de");
		System.setProperty("http.proxyPort", "8080");

		final String authUser = "stefan.illgen";
		final String authPassword = "52RpJdcu!@0";

		// SecurityManager securityManager = System.getSecurityManager();

		Authenticator.setDefault(new Authenticator() {
			public PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(authUser, authPassword
						.toCharArray());
			}
		});

		try {
			List<Proxy> proxies = ProxySelector.getDefault().select(
					new URI("http://www.google.com"));
			final Proxy proxy = proxies.get(0); // ignoring multiple proxies to
												// simplify code snippet
			if (proxy.type() != Proxy.Type.DIRECT) {

				// -Dhttp.proxyHost=http://aero.saxsys.de -Dhttp.proxyPort=8080
				// -Dhttp.proxyUser=stefan.illgen
				// -Dhttp.proxyPassword=52RpJdcu!@0 -Dhttp.proxySet=true
				// -Djava.net.useSystemProxies=true
				System.setProperty("http.proxyUser", authUser);
				System.setProperty("http.proxyPassword", authPassword);

				Properties properties = System.getProperties();
				System.out.println(properties);

				// you can change that to dialog using separate Stage
				// final TextField login = new TextField("login");
				// final PasswordField pwd = new PasswordField();
				// Button btn = new Button("Submit");
				// btn.setOnAction(new EventHandler<ActionEvent>() {
				// @Override
				// public void handle(ActionEvent t) {
				// System.setProperty("http.proxyUser", login.getText());
				// System.setProperty("http.proxyPassword", pwd.getText());
				// }
				// });
			}
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}

	}

}
