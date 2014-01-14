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
import javafx.scene.layout.BorderPane;

import org.devel.javafx.navigation.prototype.viewmodel.SearchRouteViewModel;

import de.saxsys.jfx.mvvm.base.view.View;

/**
 * @author stefan.illgen
 *
 */
public class SearchRouteView extends View<SearchRouteViewModel> {

	@FXML
    private Label finishLbl;

    @FXML
    private TextField finishTf;

    @FXML
    private Button searchBtn;

    @FXML
    private BorderPane searchRoutePane;

    @FXML
    private Label startLbl;

    @FXML
    private TextField startTf;


    @FXML
    void finishChanged(KeyEvent event) {
    }

    @FXML
    void startChanged(KeyEvent event) {
    }
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		
		
	}

}
