package org.devel.javafx.navigation.prototype.view;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.layout.HBox;

import org.devel.javafx.scene.control.RouteMapView;

import de.saxsys.jfx.mvvm.base.view.ViewWithoutViewModel;

/**
 * @author stefan.illgen
 * 
 */
public class MapView extends ViewWithoutViewModel {

	@FXML
	private HBox mapViewHBox;

	@FXML
	private RouteMapView routeMapView;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

	}

	public StringProperty startPositionProperty() {
		return routeMapView.startPositionProperty();
	}

	public StringProperty finishPositionProperty() {
		return routeMapView.finishPositionProperty();
	}

}
