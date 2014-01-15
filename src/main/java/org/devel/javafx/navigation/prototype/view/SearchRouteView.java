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
    }

    @FXML
    void startChanged(KeyEvent event) {
    	WebEngine webEngine = mapViewController.getWebEngine();
//    	webEngine.executeScript("document.goToLocation(\""+ searchBox.getRawText()+"\")");
    }
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		getViewModel().initialize(mapViewController.getViewModel());
		
		startTf.textProperty().bindBidirectional(getViewModel().startProperty());
		finishTf.textProperty().bindBidirectional(getViewModel().finishProperty());
	}

}
