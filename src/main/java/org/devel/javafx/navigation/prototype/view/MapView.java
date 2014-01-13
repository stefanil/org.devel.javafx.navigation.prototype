package org.devel.javafx.navigation.prototype.view;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;

import org.devel.javafx.navigation.prototype.viewmodel.MapViewModel;

import de.saxsys.jfx.mvvm.base.view.View;

/**
 * @author stefan.illgen
 *
 */
public class MapView extends View<MapViewModel> {
	
	@FXML
    private BorderPane mapView;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		
	}

}
