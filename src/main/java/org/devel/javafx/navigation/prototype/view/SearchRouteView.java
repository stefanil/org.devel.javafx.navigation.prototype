/**
 * 
 */
package org.devel.javafx.navigation.prototype.view;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

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

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		// init VM
		getViewModel().initialize();

		// bind V 2 VM after Web Engine has been loaded by maps view
		startTf.textProperty()
				.bindBidirectional(getViewModel().startProperty());
		finishTf.textProperty().bindBidirectional(
				getViewModel().finishProperty());
		// set route props
		mapViewController.startPositionProperty().bind(startTf.textProperty());
		mapViewController.finishPositionProperty().bind(finishTf.textProperty());
	}

	public MapView getMapView() {
		return mapViewController;
	}

}
