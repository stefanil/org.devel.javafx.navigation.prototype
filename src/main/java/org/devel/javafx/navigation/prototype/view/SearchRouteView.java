/**
 * 
 */
package org.devel.javafx.navigation.prototype.view;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker.State;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;

import org.devel.javafx.navigation.prototype.viewmodel.SearchRouteViewModel;

import de.saxsys.jfx.mvvm.base.view.View;

/**
 * @author stefan.illgen
 * 
 */
public class SearchRouteView extends View<SearchRouteViewModel> {

	@FXML
	private MapView mapViewController;

	@FXML
	private Label finishLbl;

	@FXML
	private TextField finishTf;

	@FXML
	private Button searchBtn;

	@FXML
	private VBox searchRouteVBox;

	@FXML
	private Label startLbl;

	@FXML
	private TextField startTf;

	@FXML
	void finishChanged(KeyEvent event) {
		calcRoute();
	}

	@FXML
	void startChanged(KeyEvent event) {
		calcRoute();
	}

	private void calcRoute() {
		WebEngine webEngine = mapViewController.getWebEngine();
		webEngine.executeScript("calcRoute(\"" + startTf.getText() + "\", \"" + finishTf.getText() + "\")");
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		// init VM
		getViewModel().initialize(mapViewController.getViewModel());

		// wait for webEngine to initiate displaying of the route
		mapViewController.getWebEngine().getLoadWorker().stateProperty()
				.addListener(new ChangeListener<State>() {
					@Override
					public void changed(ObservableValue<? extends State> arg0,
							State arg1, State newState) {
						if (newState == State.SUCCEEDED) {
							// calculate route
							calcRoute();
							// remove change listener
							mapViewController.getWebEngine().getLoadWorker()
									.stateProperty().removeListener(this);
						}						
					}
				});
		
		// bind V 2 VM after Web Engine has been loaded by maps view
		startTf.textProperty()
				.bindBidirectional(getViewModel().startProperty());
		finishTf.textProperty().bindBidirectional(
				getViewModel().finishProperty());
	}

	public MapView getMapView() {
		return mapViewController;
	}

}
