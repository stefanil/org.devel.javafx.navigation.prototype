package org.devel.javafx.navigation.prototype.view;

import java.net.URL;
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
import org.devel.javafx.navigation.prototype.util.Properties;
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

		// load proxy configuration and load monitors ..
		new Properties().loadProxyConf();
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
						if(Configuration.DEBUG)
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

}
