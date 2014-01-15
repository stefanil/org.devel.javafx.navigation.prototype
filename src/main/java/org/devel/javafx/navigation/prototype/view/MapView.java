package org.devel.javafx.navigation.prototype.view;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker;
import javafx.concurrent.Worker.State;
import javafx.fxml.FXML;
import javafx.scene.layout.HBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.util.Callback;

import org.devel.javafx.navigation.prototype.Configuration;
import org.devel.javafx.navigation.prototype.util.Properties;

import com.sun.javafx.scene.web.Debugger;

import de.saxsys.jfx.mvvm.base.view.ViewWithoutViewModel;

/**
 * @author stefan.illgen
 * 
 */
public class MapView extends ViewWithoutViewModel {

	@FXML
	private HBox mapViewHBox;

	@FXML
	private WebView mapWebView;

	private WebEngine webEngine;

	private Worker<Void> loadWorker;

	private StringProperty startPosition;

	private StringProperty finishPosition;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		// get the web engine
		webEngine = mapWebView.getEngine();

		// load proxy configuration and load monitors ..
		new Properties().loadProxyConf();
		loadMonitors(webEngine);
		initializeDebugger(webEngine);

		// load url into the engine
		final URL urlGoogleMaps = getClass().getResource(
				Configuration.GOOGLEMAPS_HTML);
		webEngine.load(urlGoogleMaps.toExternalForm());
	}

	private void loadMonitors(WebEngine webEngine) {

		loadWorker = webEngine.getLoadWorker();

		// monitor state
		loadWorker.stateProperty().addListener(
				new ChangeListener<Worker.State>() {
					public void changed(ObservableValue<? extends State> ov,
							State oldValue, State newValue) {
						if (Configuration.DEBUG)
							System.err.printf(
									"State changed, old: %s, new: %s%n",
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

	@SuppressWarnings("deprecation")
	private void initializeDebugger(WebEngine webEngine) {
		Debugger debugger = webEngine.impl_getDebugger();
		debugger.setMessageCallback(new Callback<String, Void>() {
			@Override
			public Void call(String arg0) {
				if (Configuration.DEBUG)
					System.err.println(arg0);
				return null;
			}
		});
	}

	/**
	 * 
	 * @return
	 */
	public WebEngine getWebEngine() {
		return webEngine;
	}

	public void bindRoute(StringProperty startPosition,
			StringProperty finishPosition) {
		
		this.startPosition = startPosition;
		this.finishPosition = finishPosition;

		// listen for webEngine to initiate displaying of the route
		getWebEngine().getLoadWorker().stateProperty()
				.addListener(new ChangeListener<State>() {
					@Override
					public void changed(ObservableValue<? extends State> state,
							State arg1, State newState) {
						if (newState == State.SUCCEEDED) {
							// remove change listener
							state.removeListener(this);
							// calculate route
							calcRoute();
						}
					}
				});

	}

	public void calcRoute() {
		webEngine.executeScript("calcRoute(\"" + startPosition.get() + "\", \""
				+ finishPosition.get() + "\")");
	}

}
